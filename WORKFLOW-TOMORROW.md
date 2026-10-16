# TipSync — Tomorrow’s work day workflow

**Goal:** Lock brand visuals, nail core screens, start building v1 (gig dashboard for head queens).

**Time budget:** One focused day (~6–8 hrs). Adjust blocks if you have less time.

---

## Start of day (15 min)

- [ ] Open `TipSync/PRODUCT.md` — skim v1 scope (dashboard only; tip jar back burner).
- [ ] Optional skim: `TipSync/REVENUE.md` — pricing tiers if building paywalls tomorrow.
- [ ] Review assets in `assets/`:
  - `tipsync-icon-concept.png` ← **primary brand reference**
  - `tipsync-phone-gui-mockup.png` ← **UI direction**
- [ ] Pick **one** lips-as-**i** rule for all tomorrow renders (see Block A).
- [ ] Create a scratch folder: `TipSync/design/` for exports from tomorrow’s renders.

**Decision to make once (don’t re-debate all day):**

| Choice | Recommendation |
|--------|----------------|
| Logo for app | First render style; fix **Tip** spelling + lips = **i** (horizontal mouth, tall narrow column, dot above, **no oval frame**) |
| v1 user | Head queen only |
| Build stack | TBD — if undecided, default: **Next.js or Vite + React**, mobile-first CSS, deploy later |

---

## Block A — Brand renders (90–120 min)

**Output:** 3–4 PNGs in `TipSync/design/`

Work in this order (each prompt builds on the last):

| # | Asset | Spec | Done |
|---|--------|------|------|
| A1 | **App icon** | Square, first-render glam; lips read as **i**; spell **Tip** not Top | [ ] |
| A2 | **Wordmark** | Wide linear lockup (header/marketing); same lips rule as A1 | [ ] |
| A3 | **Splash / loading** | Icon + tagline: *Lip sync sells the show. TipSync saves the business.* | [ ] |
| A4 | **Favicon / small** | Simplified: pink lips + dot on black (readable at 32px) | [ ] |

**Lips-as-i checklist (every render):**

- Horizontal mouth (normal kiss orientation)
- Glyph column **height > width**
- Dot centered above (tittle)
- No vertical slit, no oval frame
- **T–i–p** spacing matches normal typography

**Stop rule:** When A1 looks right, duplicate that lips treatment across A2–A4 — don’t redesign per file.

---

## Block B — GUI renders (90–120 min)

**Output:** 4 phone mockups in `TipSync/design/screens/`

Use **same** brand as A1 + `tipsync-phone-gui-mockup.png` layout patterns.

| # | Screen | Must show | Done |
|---|--------|-----------|------|
| B1 | **Home** | This week, unpaid banner, tabs, bottom nav (already have draft — **refine** or redo) | [ ] |
| B2 | **Gig detail** | Venue, city, pipeline status, guarantee/tips, **cast ledger** (owed/paid toggles) | [ ] |
| B3 | **Inbox** | Inquiry list, status, quick actions | [ ] |
| B4 | **New gig / inquiry** | Minimal form: city, venue, date, rate, role | [ ] |

**Optional if time:** B5 Money (month/YTD), B6 Export confirmation.

**Stop rule:** Screens should be readable at phone scale — big type, pink accent only for actions/alerts.

---

## Block C — Design tokens (30 min)

**Output:** `TipSync/design/TOKENS.md` (copy values from approved A1/B1)

| Token | Value (draft from mockups) |
|-------|----------------------------|
| Background | `#0A0A0A` / near black |
| Accent | Electric hot pink `#FF1493` (tune from final icon) |
| Text primary | `#FFFFFF` |
| Text muted | `#9CA3AF` |
| Serif (headings) | TBD — match icon (e.g. Playfair Display) |
| Script (Sync only) | TBD — match icon cursive |
| UI sans | Inter / system-ui |
| Radius cards | 12–16px |
| Min tap target | 44px |

- [ ] Pull hex values from final A1 screenshot (eyedropper).
- [ ] Note which font substitutes if exact icon fonts aren’t licensed.

---

## Block D — Spec lock (45 min)

**Output:** Update `PRODUCT.md` only where visuals changed; add `TipSync/SCREENS.md`

- [ ] List **every field** on Gig + Cast ledger (from B2).
- [ ] List **pipeline statuses** (Inquiry → … → Paid).
- [ ] Mark **out of scope tomorrow:** tip jar, wardrobe, tax receipts, sub-queen login.
- [ ] Add **open questions** section at bottom if anything unresolved.

**SCREENS.md skeleton:**

1. Home
2. Gig detail
3. Inbox
4. New inquiry/gig
5. Nav map (wireflow one page)

---

## Block E — Build scaffold (2–3 hrs)

**Output:** Runnable app skeleton in `TipSync/app/` (or repo root — pick one and stay there)

**Order:**

1. [ ] `npm create` / init project (mobile-first, TypeScript recommended).
2. [ ] Global styles from `TOKENS.md` (CSS variables).
3. [ ] Layout: bottom nav shell (Home, Inbox, Gigs, Profile) — routes can be stubbed.
4. [ ] **Home** page: static copy of B1 (hardcoded Dallas/Houston cards).
5. [ ] **Gig detail** page: static copy of B2 (one mock gig + 3 cast rows).
6. [ ] Placeholder logo in header (use A1 when ready).

**Do not tomorrow (scope creep):**

- Auth / database
- Real payments
- Tip jar QR
- Export CSV

**Done when:** Phone emulator shows Home + Gig detail matching mockups “close enough.”

---

## Block F — EOD wrap (20 min)

- [ ] Commit or zip: `design/`, updated `PRODUCT.md`, `SCREENS.md`, `app/` scaffold.
- [ ] `TipSync/CHANGELOG-DAY.md` — 5 bullets: what shipped, what’s blocked, first task next session.
- [ ] Note **one** lips reference file: “use A1 for all future assets.”

---

## Suggested schedule (8 hr day)

| Time | Block |
|------|--------|
| 9:00 | Start of day + **A** Brand (A1 first, gate everything on it) |
| 11:00 | **B** GUI screens |
| 1:00 | Lunch |
| 1:45 | **C** Tokens + **D** Spec lock |
| 3:00 | **E** Build scaffold |
| 5:30 | **F** Wrap + changelog |

**Short day (4 hr):** A1 + A2 → B1 + B2 → C → E (Home only).

---

## Dependency graph

```
A1 icon approved
    ├── A2–A4 brand pack
    ├── B1–B4 GUI mocks
    └── C tokens
            └── E scaffold (visual parity)
D spec lock (can run parallel after B2)
```

---

## Success criteria for tomorrow

- [ ] Brand: One approved **icon** + **wordmark** with lips = **i**
- [ ] UI: **4 screen** mocks aligned to v1 dashboard
- [ ] Docs: `SCREENS.md` + tokens
- [ ] Code: Nav shell + **Home** + **Gig detail** static UI

---

## Reference paths

| Item | Path |
|------|------|
| Product brief | `TipSync/PRODUCT.md` |
| Icon reference (favorite) | `assets/tipsync-icon-concept.png` |
| GUI reference | `assets/tipsync-phone-gui-mockup.png` |
| Tomorrow outputs | `TipSync/design/` |

---

*Workflow v1 — adjust blocks in the morning if priorities shift.*
