package com.vesperrun.tipsync.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Bg = Color(0xFF0A0A0A)
private val Accent = Color(0xFFFF1493)
private val Muted = Color(0xFF9CA3AF)

private val Scheme =
    darkColorScheme(
        primary = Accent,
        onPrimary = Color.White,
        background = Bg,
        onBackground = Color.White,
        surface = Color(0xFF141414),
        onSurface = Color.White,
        onSurfaceVariant = Muted,
    )

@Composable
fun TipSyncTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Scheme, content = content)
}
