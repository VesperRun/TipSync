import Link from "next/link";
import { GigCard } from "@/components/GigCard";
import { castOwedAll, mockGigs } from "@/lib/mock-gigs";

export default function HomePage() {
  const owed = castOwedAll(mockGigs);
  const upcoming = mockGigs.filter(
    (g) => g.status === "Booked" || g.status === "Show day",
  );

  return (
    <main className="page">
      {owed > 0 ? (
        <Link href="/unpaid" className="banner-unpaid" role="status">
          <strong>${owed}</strong> cast still owed across shows
        </Link>
      ) : null}

      <h2 className="section-title">This week</h2>
      {upcoming.length === 0 ? (
        <p className="muted">No booked shows in the next 7 days.</p>
      ) : (
        upcoming.map((gig) => <GigCard key={gig.id} gig={gig} />)
      )}

      <h2 className="section-title">Recent</h2>
      {mockGigs
        .filter((g) => g.status === "Completed" || g.status === "Paid")
        .map((gig) => (
          <GigCard key={gig.id} gig={gig} />
        ))}
    </main>
  );
}
