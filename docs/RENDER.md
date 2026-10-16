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
| GET | `/health` | Always 200 if process is up |
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
