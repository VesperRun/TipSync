package com.vesperrun.tipsync.data

enum class GigStatus(val label: String) {
    Inquiry("Inquiry"),
    Negotiating("Negotiating"),
    Booked("Booked"),
    ShowDay("Show day"),
    Completed("Completed"),
    Paid("Paid"),
    ;

    companion object {
        fun fromLabel(label: String): GigStatus =
            entries.firstOrNull { it.label == label } ?: Inquiry
    }
}
