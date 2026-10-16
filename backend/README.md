# TipSync API (stateless)

No operator database. Stripe is the only user/billing store. Gigs never hit this service.

## Run locally

```powershell
cd backend
python -m venv .venv
.\.venv\Scripts\Activate.ps1
pip install -r requirements.txt
copy .env.example .env
uvicorn main:app --reload --port 8000
```

## Routes

| Method | Path | Purpose |
|--------|------|---------|
| GET | `/health` | Liveness |
| GET | `/entitlement?email=` | Stripe subscription check |
| GET | `/releases/latest?email=` | Gated APK metadata + URL |

See [../docs/RENDER.md](../docs/RENDER.md).
