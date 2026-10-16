package com.vesperrun.tipsync.ui

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private val parser = DateTimeFormatter.ISO_LOCAL_DATE_TIME
private val display =
    DateTimeFormatter.ofPattern("EEE, MMM d · h:mm a", Locale.getDefault())

fun formatCallTime(iso: String): String =
    runCatching {
        display.format(LocalDateTime.parse(iso, parser))
    }.getOrElse { iso }
