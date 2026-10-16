# Screen wireframes (text — Phase 0)

Implement from this doc + [TOKENS.md](../TOKENS.md). PNG mocks optional.

## Global

- Background `#0A0A0A`, accent `#FF1493`, cards `#141414`, radius ~14dp
- Bottom nav: Home · Inbox · Gigs · Profile (hidden on gig detail / unpaid drill-down)
- Min tap height 48dp on toggles

## Home

```
[ Unpaid banner — accent fill if cast owed > 0 → tap opens Unpaid ]

This week
┌─────────────────────────────┐
│ Rose Room          [Booked] │
│ Dallas · Fri Oct 17 · 10pm  │
└─────────────────────────────┘
```

## Gig detail (post-show hero — reference: Completed show)

```
← Back

[Houston] [Completed]
Eagle Houston
Sat, Oct 18 · 9:00 PM

── Booking ──
Role · Host
Booker · …
Source · …
Notes · …

── Show income ──
Guarantee $400     [Received | Not received]
Tips $620          cash, Venmo
Other $0
Total $1020

── Cast ledger ──
$125 still owed this show
Crystal · Feature · $100     [Paid]
DJ Mike · DJ · $75           [Owed]
…

(Post-show: income + cast toggles are the hero — target <90s in Phase 1)
```

## Inbox

```
Inquiry / Negotiating list (same card as Home)
Footer hint: open gig · Promote to Booked (Phase 1)
```

## New inquiry (Phase 1)

Fields: city, venue, date, guarantee, role → Inquiry or Booked.

## Unpaid (Phase 0 stub route)

```
Cast owed by show + guarantees not marked received
Tap row → gig detail
```

## Profile

Display-only copy: not a payment processor; cities / handles in Phase 1.
