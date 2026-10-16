# TipSync — Screen spec (v1 MVP)

**Android (Compose)** — ship target. Bottom nav on all main screens. (`app/` = optional static mock only.)

## Nav

| Tab | Route | Purpose |
|-----|-------|---------|
| Home | `/` | This week + unpaid banner |
| Inbox | `/inbox` | Inquiry / negotiating |
| Gigs | `/gigs` | All gigs list |
| Profile | `/profile` | Cities, venues, handles (display only) |

## 1. Home

- **Unpaid banner** (if cast or guarantee owed): total cast owed, link to Unpaid detail
- **This week**: cards sorted by date; city tag; venue; status chip
- Tap card → Gig detail

## 2. Gig detail (hero: post-show)

**Header:** venue, city, pipeline status

**Booking:** date/time, role, booker, source, notes (read/edit later)

**Income (head queen):** guarantee (+ received toggle), tips (+ method tags), other, total

**Cast ledger:** rows — name, role, agreed amount, Owed | Paid toggle, optional note

**Primary action when Completed:** fast path — income + cast toggles (&lt;90s target)

## 3. Inbox

- List gigs with status `Inquiry` or `Negotiating`
- Quick action: open gig, promote to Booked

## 4. New inquiry / gig

Minimal create: city, venue, date, rate/guarantee, role → saves as Inquiry or Booked

## 5. Unpaid (v1.1 route or Home banner target)

- Guarantees marked not received
- Cast lines still Owed, grouped by gig

## 6. Money / Export (Phase 2)

- Month + YTD totals
- Export CSV confirmation

## Pipeline statuses

`Inquiry` → `Negotiating` → `Booked` → `Show day` → `Completed` → `Paid`

## Out of scope (v1)

Tip jar QR, wardrobe, tax receipts, sub-queen login, payment processing
