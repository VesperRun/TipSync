# TipSync

**Lip sync sells the show. TipSync saves the business.**

*Putting on her heels — product brief v0.1 (draft for adjustment)*

---

## Elevator pitch

**TipSync** is a mobile-first micro SaaS for **Texas drag performers** running an independent performance business across sprawling metros (Dallas, Houston, San Antonio, Austin, and beyond).

The name parallels **lip sync** — the precision that makes drag *drag*. **Tip sync** is the same idea applied to operations: **lock your gigs, income, and cast obligations to the show night** — not to a scattered stack of DMs, screenshots, and Venmo memory.

TipSync does **not** process payments or route money to bank accounts. The **head queen** gets paid (venue, door, tips); she **distributes** to her cast via **Cash App, Venmo, and QR codes she already uses**. TipSync is the **ledger and pipeline** so nothing falls off between cities.

---

## Brand & positioning

| Element | Direction |
|--------|-----------|
| **Name** | TipSync (play on lip sync / lip synchronization) |
| **Metaphor** | Lip sync = mouth locked to the track. Tip sync = money and gigs locked to the show. |
| **Market** | Texas independent drag — multi-city, multi-venue, high travel |
| **Wedge** | Head queens running cast shows who collect first and pay out second |
| **Not** | Generic booking software, payment processor, or “another calendar” |

**Tagline options**

- *Lip sync sells the show. TipSync saves the business.*
- *Sync your gigs to the show — from Dallas to Houston and back.*
- *Performance tight. Payouts tighter.*

---

## Problem

Texas drag performers operate as **real small businesses** without business infrastructure. Metro areas are **large and fragmented** — a working queen is rarely anchored to one venue. She juggles:

- **Booking inquiries** across Instagram, Facebook, texts, and venue contacts
- **Gig calendars** that live in different places (double-books, forgotten call times)
- **Tips and digital payments** (cash, Cash App, Venmo, Zelle) with no clean tie to a specific night
- **Cast economics** when she is the **head queen**: money lands on her; she owes features, openers, DJs
- **Merchandise** (tracked mentally or not at all)
- **Tax season** without a per-gig income trail

All of this often happens **while performing in restrictive drag** — glue, corsets, gloves, low light, no laptop. Operational slip equals **lost money and burned relationships** (“did I pay Crystal or the other Crystal?”).

---

## Solution (v1)

A **gig and revenue dashboard**: one mobile-optimized pipeline per show that consolidates:

1. **Booking inquiries** → negotiation → booked
2. **Venue & show logistics** (city, venue, call time, role, rates, notes)
3. **Show income** (guarantee, tips, other) — what hit the **head queen**
4. **Cast ledger** — who she owes from that night and whether she’s marked them **paid** (after distro on her own apps)

**Out of scope for v1**

- Aggregated tip jar / fan-facing QR checkout (**back burner**)
- Automatic payment routing or bank payouts (**explicitly excluded**)
- Wardrobe & track manager (**roadmap**)
- Tax receipt scanner / write-off tracker (**roadmap**)

---

## User model

### Primary persona: Head queen

- Books or hosts cast shows across **multiple Texas cities**
- **Collects** guarantees, tips, and pooled money **first**
- **Distributes** to subordinate queens, DJ, etc. via **Cash App / Venmo** (and their QR codes) — **outside TipSync**
- Needs: calendar sanity, inquiry capture, per-show money picture, **outstanding cast balances**

### Secondary persona (future): Subordinate queen

- On the lineup; owed money from the head queen
- v1 may be **name-only on cast list** (no login)
- Later: invited account, read-only “owed / paid” for her gigs

### Fan / audience

- Not in v1 product surface
- Future tip jar: tips **head queen only**; she still distro’s manually

---

## Money flow (how the world works)

```
Audience / venue
        ↓
   Head queen (paid first)
        ↓
   Cash App / Venmo / cash (her tools, not TipSync)
        ↓
   Subordinate queens, DJ, etc.
```

**TipSync tracks. TipSync does not transfer.**

When the **aggregated tip jar** returns (back burner), it will be **one queen per QR** — the head queen’s link only. Cast splits remain **her job** after the show, same as today.

---

## Core feature: Gig & revenue dashboard

### Gig pipeline (status)

```
Inquiry → Negotiating → Booked → Show day → Completed → Paid
```

Each **gig** is the source of truth for one performance night (or engagement).

### Gig record — fields (draft)

**Booking & logistics**

- Status (pipeline stage)
- Date & time (call time, show time)
- **City** (Dallas, Houston, San Antonio, Austin, other)
- **Venue** (preset + free text)
- Role (host, feature, guest, opener, etc.)
- Booker / contact
- Inquiry source (IG, venue, referral, etc.)
- Notes & attachments (photo of contract / agreement)

**Money (head queen)**

- **Guarantee** — amount, received / owed
- **Tips** — amount, payment method tags (cash, Venmo, Cash App, Zelle)
- **Other income** (door, merch bucket optional v1.1)
- **Total show income**
- Payment notes (reference, “venue pays Monday,” etc.)

**Cast ledger (per gig)**

- Cast member name (and role: feature, opener, DJ)
- **Agreed amount** owed
- Status: **Owed** | **Paid**
- Optional note (“paid via Venmo 3/17”)

### Dashboard views

| View | Purpose |
|------|---------|
| **This week** | Upcoming gigs by city; conflicts visible |
| **Inbox** | Open inquiries needing response |
| **Unpaid** | Guarantees still owed; **cast still owed** across shows |
| **Money** | Month / YTD income from logged gigs |
| **Export** | CSV or summary for tax prep (per gig, not CPA software) |

### Mobile-first / backstage constraints

- Large tap targets, minimal typing
- Quick log in **under 90 seconds** between numbers
- Works on phone in dressing room lighting
- Templates for common inquiry replies (future-friendly)

### Texas-tailored details

- City + venue tags and **memory** (“last host rate at this venue”)
- Multi-metro calendar on one screen
- Optional later: travel buffer hints between cities

---

## Roadmap features (not v1)

### Aggregated tip jar — **back burner**

- Personalized **QR** → mobile tip landing page
- **One queen gets paid at a time** (head queen only)
- Deep links to **her** Venmo / Cash App / PayPal — no bank routing through TipSync
- Optional preset amounts; session **attached to gig** in dashboard
- **No** multi-recipient checkout, **no** automatic split to DJ/dancers on fan page

### Wardrobe & track manager — **extra**

- Visual inventory: wigs, shoes, garments
- Wear count / **outfit repetition** warnings per venue
- Music files organized per gig

### Tax write-off tracker — **extra**

- Receipt photos (makeup, rhinestones, costuming)
- Expense categories
- Pairs with dashboard income export at tax time

---

## Use case proof (head queen week)

**Without TipSync**

- Houston inquiry lost in DMs while in face in Dallas
- Double-book risk between group chat and Notes
- Saturday cast show: $620 cash, $180 Venmo, owe DJ + three girls — Sunday crash, Monday fuzzy Venmos, Wednesday drama

**With TipSync**

- Inquiry → gig pipeline; thumb templates
- One calendar, city-tagged
- One **Saturday gig card**: income in, cast **owed → paid** checklist
- She still Venmos on her phone; TipSync remembers **who from which show**
- Year-end: export gig income instead of Venmo archaeology

**One-line sell**

> *I run shows across Texas, money lands on me, I pay the girls on Venmo. TipSync is the only place that knows what Saturday was worth and who I still owe.*

---

## Competitive framing

| Tool | Gap |
|------|-----|
| Google Calendar | When, not money, not cast, not inquiry |
| Venmo history | No gig, no venue, no “owed to cast” |
| Notes / spreadsheets | Not mobile-backstage, not Texas-gig-shaped |
| Generic gig apps | Not drag show economics (head queen hub) |

---

## Business model

**Full detail:** [`REVENUE.md`](./REVENUE.md)

| Stream | v1? | Summary |
|--------|-----|---------|
| **Freemium → subscription** | Yes | Free: capped gigs; paid: cast ledger, export, multi-metro |
| **Tiers** | Yes | Solo ~$8 · Head queen ~$12–15 · Troupe ~$22–25 · Annual ~$79–99 |
| **One-time products** | Yes | Inquiry templates, rate/contract packs ($9–29) |
| **Module add-ons** | Later | Wardrobe, tax receipts (+$4/mo each) |
| **Venue/booker B2B** | Later | $29–99/mo when bookers follow queens |
| **Sponsorships / affiliates** | Later | Drag-adjacent brands; disclose, don’t spam |
| **Tip platform %** | **No** | Only if payments IF; not v1 |

> *Free to track the hustle. Pay when you run cast and file the bag.*

**v1 rule:** No take rate on tips — TipSync does not process payments.

---

## Success metrics (draft)

- Gigs logged per active user per month
- % of cast shows with complete cast ledger within 48h of show
- Inquiry → booked conversion (user-reported or status change)
- Retention at 30 / 90 days across touring season
- Export used at least once before tax deadline (qualitative win)

---

## MVP build order

1. Auth + head queen profile (cities, venues, payment handles for **display/logging only**)
2. Gig CRUD + pipeline statuses
3. Inquiry inbox (create from inquiry, promote to gig)
4. Dashboard: this week, unpaid (guarantee + cast), money summary
5. Cast ledger on gig
6. Income export (CSV)
7. Landing / waitlist (Texas positioning)

**Explicitly later:** tip jar QR, sub-queen logins, wardrobe, receipts

---

## Locked decisions (Phase 0)

| # | Decision | v1 |
|---|----------|-----|
| 1 | **Head queen only** — no cast login | Cast = names on ledger |
| 2 | Sub-queen portal | **v1.1+** — read-only “owed / paid” if ever |
| 3 | Inquiry capture | **Manual in-app only** — no email forward / form link |
| 4 | Merch on gig card | **Post-MVP** |
| 5 | Name / domain | **TipSync** working title; legal/domain check before public VP |
| 6 | Ship client | **Android (Room)** — D2C APK; optional `app/` mock only |
| 7 | Operator data | **No gig collection** — Stripe only ([docs/DATA-POLICY.md](./docs/DATA-POLICY.md)) |

---

## Legal & compliance (lightweight)

- TipSync **does not** hold funds or route to bank accounts in v1
- Copy and terms should state: **payment links and ledger for organization only**
- Not tax advice; export is **input for performer or CPA**

---

## Document history

| Version | Date | Notes |
|---------|------|-------|
| v0.1 | 2026-05-31 | Initial write-up from product discovery session |
| v0.2 | 2026-05-31 | Revenue model → `REVENUE.md` |

---

*Adjust freely — heels first, refine the runway second.*
