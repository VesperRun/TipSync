# Render setup (cloud)

TipSync on Render: **Web Service only** — no operator Postgres, no user data store.

## What Render runs

**FastAPI** (`backend/`): stateless entitlement + update delivery.

| Endpoint (draft) | Purpose |
|------------------|---------|
| `GET /health` | Uptime |
| `GET /entitlement` | Email → live **Stripe** subscription check → unlock cast/export + updates |
| `GET /releases/latest` | Gated APK URL if Stripe says active (or founding coupon/metadata in Stripe) |
| Stripe Checkout / Portal | Links only; checkout hosted by Stripe |

**Environment (no `DATABASE_URL`):**

- `STRIPE_SECRET_KEY`
- `STRIPE_WEBHOOK_SECRET` (optional; can rely on live Stripe API reads only)
- `SECRET_KEY` — short-lived signed tokens if needed (no user DB)
- `APK_DOWNLOAD_BASE` or release artifact config

## What Render does not run

- **PostgreSQL** for TipSync user/gig data
- **Streamlit** (local operator tools only, decoupled)

## Android

All gigs and cast lines stay in **Room** on the phone. **Retrofit** calls Render only for **billing entitlement** and **updates**, never to upload show data.

## Privacy

See [DATA-POLICY.md](./DATA-POLICY.md).
