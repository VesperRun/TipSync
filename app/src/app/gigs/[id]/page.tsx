import Link from "next/link";
import { notFound } from "next/navigation";
import {
  castOwedTotal,
  formatShortDate,
  gigById,
  mockGigs,
} from "@/lib/mock-gigs";

export function generateStaticParams() {
  return mockGigs.map((g) => ({ id: g.id }));
}

export default async function GigDetailPage({
  params,
}: {
  params: Promise<{ id: string }>;
}) {
  const { id } = await params;
  const gig = gigById(id);
  if (!gig) notFound();

  const showIncome = gig.guarantee + gig.tips;
  const castOwed = castOwedTotal(gig);

  return (
    <main className="page">
      <Link href="/" className="muted">
        ← Back
      </Link>

      <header className="detail-header">
        <div className="card-row">
          <span className="city-tag">{gig.city}</span>
          <span className="status-chip">{gig.status}</span>
        </div>
        <h1>{gig.venue}</h1>
        <p className="muted">{formatShortDate(gig.callTime)}</p>
        {gig.status === "Completed" || gig.status === "Paid" ? (
          <p className="role accent-text">Post-show — log income and mark cast paid</p>
        ) : null}
      </header>

      <section className="card">
        <h2 className="section-title">Booking</h2>
        <div className="money-row">
          <span>Role</span>
          <span>{gig.role}</span>
        </div>
      </section>

      <section className="card">
        <h2 className="section-title">Show income</h2>
        <div className="money-grid">
          <div className="money-row">
            <span>Guarantee</span>
            <span>
              ${gig.guarantee}{" "}
              {gig.guaranteeReceived ? "(received)" : "(owed)"}
            </span>
          </div>
          <div className="money-row">
            <span>Tips</span>
            <span>
              ${gig.tips}
              {gig.tipMethods.length > 0
                ? ` · ${gig.tipMethods.join(", ")}`
                : ""}
            </span>
          </div>
          <div className="money-row total">
            <span>Total</span>
            <span>${showIncome}</span>
          </div>
        </div>
      </section>

      <section className="card">
        <h2 className="section-title">Cast ledger</h2>
        {castOwed > 0 ? (
          <p className="muted">${castOwed} still owed this show</p>
        ) : (
          <p className="muted">Cast paid for this show</p>
        )}
        {gig.cast.map((line) => (
          <div key={line.id} className="cast-row">
            <div>
              <strong>{line.name}</strong>
              <span className="muted"> · {line.role}</span>
              <div className="muted">${line.amount}</div>
            </div>
            <button
              type="button"
              className={line.paid ? "toggle paid" : "toggle"}
              aria-pressed={line.paid}
            >
              {line.paid ? "Paid" : "Owed"}
            </button>
          </div>
        ))}
      </section>
    </main>
  );
}
