import type { Metadata, Viewport } from "next";
import { Suspense } from "react";
import { BottomNav } from "@/components/BottomNav";
import "./globals.css";

export const metadata: Metadata = {
  title: "TipSync",
  description:
    "Gig and cast ledger for Texas drag hosts. Tracks the show; you move the money.",
  applicationName: "TipSync",
  manifest: "/manifest.webmanifest",
};

export const viewport: Viewport = {
  themeColor: "#0a0a0a",
  width: "device-width",
  initialScale: 1,
  maximumScale: 1,
  viewportFit: "cover",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        <div className="shell">
          <header className="site-header">
            <p className="logo">
              Tip<span>Sync</span>
            </p>
          </header>
          {children}
          <Suspense fallback={null}>
            <BottomNav />
          </Suspense>
        </div>
      </body>
    </html>
  );
}
