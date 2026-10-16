package com.vesperrun.tipsync.data

/** Deterministic demo seed — local only, matches mock-gigs in app/. */
object SeedGigs {
    fun gigs(): List<GigEntity> =
        listOf(
            GigEntity(
                id = "sat-houston",
                status = GigStatus.Completed.label,
                city = "Houston",
                venue = "Eagle Houston",
                callTimeIso = "2026-10-18T21:00:00",
                role = "Host",
                guarantee = 400,
                guaranteeReceived = true,
                tips = 620,
                tipMethods = "cash,Venmo",
                otherIncome = 0,
                booker = "Eagle events",
                source = "IG DM",
                notes = "Cast call 8pm door",
            ),
            GigEntity(
                id = "fri-dallas",
                status = GigStatus.Booked.label,
                city = "Dallas",
                venue = "Rose Room",
                callTimeIso = "2026-10-17T22:00:00",
                role = "Host",
                guarantee = 350,
                guaranteeReceived = false,
                tips = 0,
                tipMethods = "",
            ),
            GigEntity(
                id = "inquiry-sa",
                status = GigStatus.Inquiry.label,
                city = "San Antonio",
                venue = "Heat",
                callTimeIso = "2026-11-01T20:00:00",
                role = "Guest",
                guarantee = 200,
                guaranteeReceived = false,
                tips = 0,
                tipMethods = "",
            ),
        )

    fun cast(): List<CastLineEntity> =
        listOf(
            CastLineEntity("c1", "sat-houston", "Crystal", "Feature", 100, true),
            CastLineEntity("c2", "sat-houston", "DJ Mike", "DJ", 75, false),
            CastLineEntity("c3", "sat-houston", "Raven", "Opener", 50, false),
            CastLineEntity("c4", "fri-dallas", "Alexis", "Feature", 125, false),
        )
}
