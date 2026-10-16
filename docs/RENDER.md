# Render setup (cloud)

TipSync on Render: **one Web Service** — stateless FastAPI in `backend/`. **No PostgreSQL.** **No Streamlit.**

Gig and cast data never hit this host. See [DATA-POLICY.md](./DATA-POLICY.md).

---

## Option A — Blueprint (`render.yaml`)

1. [Render Dashboard](https://dashboard.render.com) → **New** → **Blueprint**.
2. Connect **GitHub** → `VesperRun/TipSync` (branch `main`).
3. Render reads [render.yaml](../render.yaml) at repo root and creates **`tipsync-api`**.
4. When prompted, set **secret** env vars (never commit these):
   - `STRIPE_SECRET_KEY` — Stripe Dashboard → Developers → API keys → **Secret key** (test or live).
   - `APK_DOWNLOAD_URL` — HTTPS URL to your signed APK (GitHub Release asset, S3, etc.) when you ship updates; leave empty until Phase 3.
   - `STRIPE_WEBHOOK_SECRET` — optional until you add a webhook endpoint.
5. Deploy. Note the service URL, e.g. `https://tipsync-api.onrender.com`.

**Health check:** `GET /health` → `{"status":"ok"}` (does not call Stripe).

---

## After apply (checklist)

Do this once **`tipsync-api`** shows **Live** in Render.

### 1. Confirm deploy

- **Dashboard** → **tipsync-api** → **Logs** — no crash loop; Uvicorn running.
- Copy **URL** (top of service page). Production: **`https://tipsync-api.onrender.com`**

### 2. Environment (tipsync-api → Environment)

| Variable | Now | Notes |
|----------|-----|--------|
| `SECRET_KEY` | Leave as generated | From blueprint |
| `APK_VERSION_CODE` / `APK_VERSION_NAME` | Defaults OK | Bump when you ship APK |
| `APK_DOWNLOAD_URL` | **Empty OK** | Set when you have a signed APK HTTPS link |
| `STRIPE_WEBHOOK_SECRET` | **Empty OK** | API does not use webhooks yet |
| `STRIPE_SECRET_KEY` | **Set when testing billing** | Stripe → Developers → API keys → **Secret key** (start with **test** `sk_test_…`) |

**Save changes** triggers a redeploy. Do **not** add `DATABASE_URL` or Postgres.

### 3. Verify from your machine

Replace URL if yours differs:

```powershell
curl.exe -s https://tipsync-api.onrender.com/health
```

```powershell
curl.exe -s "https://tipsync-api.onrender.com/entitlement?email=you@example.com"
```

- Health → `{"status":"ok"}`.
- Entitlement **without** Stripe key → JSON error / **503** (`Stripe not configured`).
- Entitlement **with** test key + matching Stripe customer → `{"active":true|false,...}`.

### 4. Stripe (test mode — optional today)

1. [Stripe Dashboard](https://dashboard.stripe.com/test/apikeys) → copy **Secret key** → paste into Render `STRIPE_SECRET_KEY` → redeploy.
2. **Customers** → create customer with the email you will type in the app later.
3. **Founding queen (no sub yet):** customer → **Metadata** → `founding_queen` = `true`.
4. Or attach an **active test subscription** to that customer for `active: true`.

### 5. Gated APK (Phase 3)

When you have a release:

1. Host signed APK at a **direct HTTPS URL** (GitHub Release, object storage, etc.).
2. Set `APK_DOWNLOAD_URL` in Render.
3. Test: `curl.exe -s "https://tipsync-api.onrender.com/releases/latest?email=PAID_EMAIL"` → `download_url` populated.

### 6. Record for Android (Phase 3)

Save base URL only — no gig data leaves the phone:

**Production:** `https://tipsync-api.onrender.com` — Retrofit base (entitlement + `/releases/latest` only; no gig upload).

### 7. Do not add on Render

- PostgreSQL linked to this product  
- Streamlit Web Service in the prod path  

---

## Option B — Manual Web Service

| Setting | Value |
|---------|--------|
| **Type** | Web Service |
| **Repository** | `VesperRun/TipSync` |
| **Root directory** | `backend` |
| **Runtime** | Python 3 |
| **Build command** | `pip install -r requirements.txt` |
| **Start command** | `uvicorn main:app --host 0.0.0.0 --port $PORT` |
| **Health check path** | `/health` |

**Environment variables** (same as blueprint — **no `DATABASE_URL`**):

| Variable | Required | Purpose |
|----------|----------|---------|
| `STRIPE_SECRET_KEY` | Phase 3+ | Live Stripe lookups for `/entitlement` and `/releases/latest` |
| `SECRET_KEY` | Yes | Render-generated or your signing secret (future signed URLs) |
| `APK_DOWNLOAD_URL` | When gating APK | Full HTTPS URL returned to paying clients |
| `APK_VERSION_CODE` | Optional | Integer, default `1` |
| `APK_VERSION_NAME` | Optional | Semver string, default `0.1.0` |
| `STRIPE_WEBHOOK_SECRET` | Optional | Not used by current API; for later webhooks |

---

## API routes (current)

| Method | Path | Behavior |
|--------|------|----------|
| GET | `/` | Service pointer (not a web app) |
| GET | `/health` | Always 200 if process is up |
| GET | `/docs` | FastAPI OpenAPI UI |
| GET | `/entitlement?email=` | **503** if Stripe not configured; else Stripe customer + active sub or `metadata.founding_queen=true` |
| GET | `/releases/latest?email=` | **403** if not entitled; else version + `APK_DOWNLOAD_URL` |

**Stripe-only records:** customers, subscriptions, invoices — no VesperRun user table.

---

## Verify after deploy

Replace with your Render URL:

```powershell
curl https://tipsync-api.onrender.com/health
```

```powershell
curl "https://tipsync-api.onrender.com/entitlement?email=you@example.com"
```

Expect **503** on entitlement until `STRIPE_SECRET_KEY` is set. That is correct.

---

## Android (later)

Retrofit base URL = Render service URL. Calls **only** entitlement + update checks — never gig upload.

---

## Do not create on Render

- **PostgreSQL** for TipSync users or gigs  
- **Streamlit** (local operator tools only, decoupled from prod)

---

## Landing page (Phase 2+)

Marketing static site can be a **second** Render Static Site or routes added to this service later. Not required for API-only Phase 3 billing.
