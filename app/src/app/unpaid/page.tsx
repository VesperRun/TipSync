import Link from "next/link";
import { castOwedTotal, mockGigs } from "@/lib/mock-gigs";

export default function UnpaidPage() {
  const withCastOwed = mockGigs.filter((g) => castOwedTotal(g) > 0);
  const guaranteeOwed = mockGigs.filter(
    (g) => g.guarantee > 0 && !g.guaranteeReceived,
  );

  return (
    <main className="page">
      <h1 className="section-title">Unpaid</h1>
      <p className="muted">Local ledger only — Phase 0 stub.</p>

      {guaranteeOwed.length > 0 ? (
        <>
          <h2 className="section-title">Guarantees</h2>
          {guaranteeOwed.map((g) => (
            <Link key={g.id} href={`/gigs/${g.id}`} className="card card-link">
              <strong>{g.venue}</strong>
              <span className="muted">
                {" "}
                · ${g.guarantee} not marked received
              </span>
            </Link>
          ))}
        </>
      ) : null}

      {withCastOwed.length > 0 ? (
        <>
          <h2 className="section-title">Cast owed</h2>
          {withCastOwed.map((g) => (
            <Link key={g.id} href={`/gigs/${g.id}`} className="card card-link">
              <strong>{g.venue}</strong>
              <span className="muted"> · ${castOwedTotal(g)} owed</span>
            </Link>
          ))}
        </>
      ) : null}

      {guaranteeOwed.length === 0 && withCastOwed.length === 0 ? (
        <p className="muted">All caught up.</p>
      ) : null}
    </main>
  );
}
