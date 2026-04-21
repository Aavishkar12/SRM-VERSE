package com.example.srmverse.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 🔥 CUSTOM DARK COLORS (MATCH YOUR UI)
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF00E5FF),
    background = Color(0xFF0B0F14),
    surface = Color(0xFF121821),
    onPrimary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

// 🔥 CUSTOM LIGHT COLORS
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF00BFA6),
    background = Color(0xFFF5F7FA),
    surface = Color.White,
    onPrimary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun SRMVERSETheme(
    darkTheme: Boolean, // 🔥 CONTROLLED BY YOU NOW
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}