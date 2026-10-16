"""Stateless TipSync API — Stripe entitlement and gated APK only."""

from __future__ import annotations

import os
from typing import Any

import stripe
from fastapi import FastAPI, HTTPException, Query
from pydantic import BaseModel, EmailStr

app = FastAPI(title="TipSync API", version="0.1.0")


class EntitlementResponse(BaseModel):
    active: bool
    email: str
    founding: bool = False


class ReleaseResponse(BaseModel):
    version_code: int
    version_name: str
    download_url: str | None = None


def _stripe_configured() -> bool:
    key = os.getenv("STRIPE_SECRET_KEY", "").strip()
    return bool(key)


def _entitlement_for_email(email: str) -> tuple[bool, bool]:
    """Return (subscription_active, founding). No local user DB."""
    if not _stripe_configured():
        return False, False

    stripe.api_key = os.environ["STRIPE_SECRET_KEY"]
    customers = stripe.Customer.list(email=email, limit=1)
    if not customers.data:
        return False, False

    customer = customers.data[0]
    founding = (customer.metadata or {}).get("founding_queen") == "true"

    subs = stripe.Subscription.list(customer=customer.id, status="active", limit=1)
    active = bool(subs.data) or founding
    return active, founding


@app.get("/health")
def health() -> dict[str, str]:
    return {"status": "ok"}


@app.get("/entitlement", response_model=EntitlementResponse)
def entitlement(email: EmailStr = Query(...)) -> EntitlementResponse:
    if not _stripe_configured():
        raise HTTPException(
            status_code=503,
            detail="Stripe not configured on server",
        )
    active, founding = _entitlement_for_email(email.lower())
    return EntitlementResponse(email=email.lower(), active=active, founding=founding)


@app.get("/releases/latest", response_model=ReleaseResponse)
def releases_latest(email: EmailStr = Query(...)) -> ReleaseResponse:
    if not _stripe_configured():
        raise HTTPException(status_code=503, detail="Stripe not configured on server")

    active, _ = _entitlement_for_email(email.lower())
    if not active:
        raise HTTPException(status_code=403, detail="No active subscription")

    url = os.getenv("APK_DOWNLOAD_URL", "").strip() or None
    return ReleaseResponse(
        version_code=int(os.getenv("APK_VERSION_CODE", "1")),
        version_name=os.getenv("APK_VERSION_NAME", "0.1.0"),
        download_url=url,
    )
