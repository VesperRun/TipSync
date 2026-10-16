import { GigCard } from "@/components/GigCard";
import { mockGigs } from "@/lib/mock-gigs";

export default function InboxPage() {
  const inbox = mockGigs.filter(
    (g) => g.status === "Inquiry" || g.status === "Negotiating",
  );

  return (
    <main className="page">
      <h2 className="section-title">Inbox</h2>
      {inbox.length === 0 ? (
        <p className="muted">No open inquiries.</p>
      ) : (
        inbox.map((gig) => <GigCard key={gig.id} gig={gig} />)
      )}
    </main>
  );
}
