# No operator database

TipSync does **not** use a VesperRun-hosted SQL database for user or gig data.

- **Performer data:** Android **Room** on device only.
- **Operator records:** **Stripe** only (billing / subscription).

There are no migrations to run on Render Postgres for this product. See [docs/DATA-POLICY.md](../docs/DATA-POLICY.md) and [docs/RENDER.md](../docs/RENDER.md).
