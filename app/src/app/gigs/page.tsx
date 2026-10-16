import { GigCard } from "@/components/GigCard";
import { mockGigs } from "@/lib/mock-gigs";

export default function GigsPage() {
  return (
    <main className="page">
      <h2 className="section-title">All gigs</h2>
      {mockGigs.map((gig) => (
        <GigCard key={gig.id} gig={gig} />
      ))}
    </main>
  );
}
