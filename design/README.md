# TipSync design pack (Phase 0)

Phase 0 ships **spec + tokens + wireframes in text** so Android and the optional web mock can be built without blocking on final PNG renders.

| Asset | Location |
|-------|----------|
| Color, type, tap targets | [TOKENS.md](./TOKENS.md) |
| Screen fields and nav | [../SCREENS.md](../SCREENS.md) |
| Text wireframes (Block B) | [screens/WIREFRAMES.md](./screens/WIREFRAMES.md) |
| Icon source (SVG) | [icon.svg](./icon.svg) |

**Block A (PNG renders):** when approved, add `icon.png`, `splash.png`, and `screens/*.png` here. Until then, [icon.svg](./icon.svg) feeds the PWA manifest and Android adaptive foreground.

**Phase 0 exit:** Android nav shell + post-show gig detail layout match [screens/WIREFRAMES.md](./screens/WIREFRAMES.md); decisions locked in [../PRODUCT.md](../PRODUCT.md).
