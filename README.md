# TipSync

**Lip sync sells the show. TipSync saves the business.**

Mobile gig and cast ledger for Texas drag hosts who collect first and pay the cast on their own apps. TipSync **tracks**; it does not process payments.

**Repository:** https://github.com/VesperRun/TipSync

## Data

**VesperRun does not collect gig or performer data.** Shows and cast ledgers live **on the device** (Room). The only operator-side records are **Stripe** (subscriptions). See [docs/DATA-POLICY.md](./docs/DATA-POLICY.md).

## Ship target

| Path | Role |
|------|------|
| **`android/`** | Kotlin / Compose — **D2C signed APK**; all show data on phone |
| **`backend/`** | Stateless FastAPI on **Render** — Stripe entitlement + gated APK updates |
| **`app/`** | Optional static UI mock (Next.js); not production |

No operator database. Distribution: direct download + word-of-mouth. Google Play out of scope until reopened.

## Docs

| File | Purpose |
|------|---------|
| [PRODUCT.md](./PRODUCT.md) | Product brief |
| [REVENUE.md](./REVENUE.md) | Monetization |
| [DESCRIPTOR.md](./DESCRIPTOR.md) | Positioning copy |
| [SCREENS.md](./SCREENS.md) | v1 screen spec |
| [docs/RENDER.md](./docs/RENDER.md) | Render Web Service (no Postgres) |
| [docs/DATA-POLICY.md](./docs/DATA-POLICY.md) | Operator data policy |
| [AGENTS.md](./AGENTS.md) | Lean build discipline for contributors and coding agents |

## Android (ship target)

```powershell
cd android
.\gradlew.bat assembleDebug
```

Install `app/build/outputs/apk/debug/app-debug.apk` on a device. All gig data stays in **Room** on the phone.

## Backend (Phase 3 — stateless)

```powershell
cd backend
python -m venv .venv
.\.venv\Scripts\Activate.ps1
pip install -r requirements.txt
uvicorn main:app --reload --port 8000
```

No `DATABASE_URL`. See [backend/README.md](./backend/README.md).

## Optional UI mock (`app/`)

```powershell
cd app
npm install
npm run dev
```

Open http://localhost:3000 — static mock data only.

Design tokens: [design/TOKENS.md](./design/TOKENS.md)

## License

Proprietary (VesperRun). All rights reserved. See [LICENSE](./LICENSE).
