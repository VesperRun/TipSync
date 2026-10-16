# Operator data policy (TipSync)

**VesperRun does not collect or store performer gig data.**

| Data | Where it lives |
|------|----------------|
| Gigs, cast ledger, venues, notes, export CSV | **On the queen’s phone only** (Android Room / SQLite) |
| Subscription, payment proof, customer email for billing | **Stripe** (sole operator-side records) |
| Update APK entitlement | **Render API** checks **Stripe at request time** — no operator database of users or shows |

The Render Web Service is **stateless**: no Postgres, no sync of performances, no accounts database maintained by the operator.

Support and “proof of purchase” use **Stripe Dashboard** (customers, subscriptions, invoices). The app does not send show-night financial details to the operator’s servers.

TipSync is **not a payment processor**; ledger features are local organization only.
