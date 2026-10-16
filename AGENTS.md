# TipSync — agent build discipline

Lean code only. Smallest diff that closes the user loop. Bloat is not optional polish — it hides the product.

## Every change

- One concern per diff; smallest correct change.
- Fewest dependencies that honestly carry the job — no vanity libraries or duplicate paths.
- No shims or files that only wrap another module.
- Prefer deterministic fixtures over random or flaky demo data.
- Remove dead code, unused exports, and ornamental abstraction before calling a slice done.
- Keep gig/cast/ledger logic readable without framework noise where separation is cheap.

## Before marking work done

- Would a senior engineer be embarrassed opening this repo cold?
- Can the hot path be read in one sitting? (inquiry → gig → post-show → unpaid → export)
- Does every module earn its place?

## TipSync scope (stay lean)

| Layer | Keep |
|-------|------|
| **Android** | Room = all show data; Compose screens per [SCREENS.md](./SCREENS.md); no upload/sync of gigs |
| **Backend** | Stateless FastAPI: health, Stripe entitlement, gated APK release — **no operator database** |
| **`app/`** | Optional static mock only; do not grow into a second product client |

## Anti-patterns

- Operator Postgres, cloud sync of performances, account servers holding gig rows
- Tip jar, Stripe Connect, microservices, event bus, generic rules engine
- Junk-drawer `utils/`, unused abstraction layers, env/config sprawl
- Analytics or telemetry stacks on operator side (Stripe + minimal Render logs only)
- Expanding `app/` with auth, BaaS, or production features

## Commits

Short imperative subject; one concern. No internal build vocabulary in messages.

See [docs/DATA-POLICY.md](./docs/DATA-POLICY.md) for operator data boundaries.
