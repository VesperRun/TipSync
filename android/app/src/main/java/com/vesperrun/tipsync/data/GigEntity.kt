package com.vesperrun.tipsync.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gigs")
data class GigEntity(
    @PrimaryKey val id: String,
    val status: String,
    val city: String,
    val venue: String,
    val callTimeIso: String,
    val role: String,
    val guarantee: Int,
    val guaranteeReceived: Boolean,
    val tips: Int,
    val tipMethods: String,
    val otherIncome: Int = 0,
    val booker: String = "",
    val source: String = "",
    val notes: String = "",
)

@Entity(
    tableName = "cast_lines",
    primaryKeys = ["id"],
)
data class CastLineEntity(
    val id: String,
    val gigId: String,
    val name: String,
    val role: String,
    val amount: Int,
    val paid: Boolean,
)
