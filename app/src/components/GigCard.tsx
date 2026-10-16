import Link from "next/link";
import { formatShortDate, type Gig } from "@/lib/mock-gigs";

export function GigCard({ gig }: { gig: Gig }) {
  return (
    <Link href={`/gigs/${gig.id}`} className="card gig-card">
      <div className="card-row">
        <span className="city-tag">{gig.city}</span>
        <span className="status-chip">{gig.status}</span>
      </div>
      <h2 className="venue">{gig.venue}</h2>
      <p className="muted">{formatShortDate(gig.callTime)}</p>
      <p className="role">{gig.role}</p>
    </Link>
  );
}
