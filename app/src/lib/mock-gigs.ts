export type GigStatus =
  | "Inquiry"
  | "Negotiating"
  | "Booked"
  | "Show day"
  | "Completed"
  | "Paid";

export type CastLine = {
  id: string;
  name: string;
  role: string;
  amount: number;
  paid: boolean;
};

export type Gig = {
  id: string;
  status: GigStatus;
  city: string;
  venue: string;
  callTime: string;
  role: string;
  guarantee: number;
  guaranteeReceived: boolean;
  tips: number;
  tipMethods: string[];
  cast: CastLine[];
};

export const mockGigs: Gig[] = [
  {
    id: "sat-houston",
    status: "Completed",
    city: "Houston",
    venue: "Eagle Houston",
    callTime: "2026-10-18T21:00:00",
    role: "Host",
    guarantee: 400,
    guaranteeReceived: true,
    tips: 620,
    tipMethods: ["cash", "Venmo"],
    cast: [
      { id: "c1", name: "Crystal", role: "Feature", amount: 100, paid: true },
      { id: "c2", name: "DJ Mike", role: "DJ", amount: 75, paid: false },
      { id: "c3", name: "Raven", role: "Opener", amount: 50, paid: false },
    ],
  },
  {
    id: "fri-dallas",
    status: "Booked",
    city: "Dallas",
    venue: "Rose Room",
    callTime: "2026-10-17T22:00:00",
    role: "Host",
    guarantee: 350,
    guaranteeReceived: false,
    tips: 0,
    tipMethods: [],
    cast: [
      { id: "c4", name: "Alexis", role: "Feature", amount: 125, paid: false },
    ],
  },
  {
    id: "inquiry-sa",
    status: "Inquiry",
    city: "San Antonio",
    venue: "Heat",
    callTime: "2026-11-01T20:00:00",
    role: "Guest",
    guarantee: 200,
    guaranteeReceived: false,
    tips: 0,
    tipMethods: [],
    cast: [],
  },
];

export function castOwedTotal(gig: Gig): number {
  return gig.cast.filter((c) => !c.paid).reduce((s, c) => s + c.amount, 0);
}

export function castOwedAll(gigs: Gig[]): number {
  return gigs.reduce((s, g) => s + castOwedTotal(g), 0);
}

export function gigById(id: string): Gig | undefined {
  return mockGigs.find((g) => g.id === id);
}

export function formatShortDate(iso: string): string {
  return new Date(iso).toLocaleDateString(undefined, {
    weekday: "short",
    month: "short",
    day: "numeric",
    hour: "numeric",
    minute: "2-digit",
  });
}
