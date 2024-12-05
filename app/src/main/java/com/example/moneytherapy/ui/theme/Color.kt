package com.example.moneytherapy.ui.theme

import androidx.compose.ui.graphics.Color






// Base colors that don't change between themes
val Gold = Color(0xFFB8860B)
val Green = Color(0xFF4CAF50)
val Red = Color(0xFFFF5C5C)

// Light Theme Colors
val LightPrimary = Color(0xFFFFFFFF)
val LightSecondary = Color(0xFFF5F5F5)
val LightTextPrimary = Color(0xFF121212)
val LightTextSecondary = Color(0xFF666666)
val LightSurface = Color(0xFFFFFFFF)
val LightBackground = Color(0xFFF8F8F8)

// Dark Theme Colors
val DarkPrimary = Color(0xFF121212)
val DarkSecondary = Color(0xFF1C1C1E)
val DarkTextPrimary = Color(0xFFE5E5E5)
val DarkTextSecondary = Color(0xFFA9A9A9)
val DarkSurface = Color(0xFF1C1C1E)
val DarkBackground = Color(0xFF000000)

// Custom color schemes for both themes
object ThemeColors {
    val LightColorScheme = androidx.compose.material3.lightColorScheme(
        primary = Gold,
        secondary = LightSecondary,
        background = LightBackground,
        surface = LightSurface,
        onPrimary = LightTextPrimary,
        onSecondary = LightTextSecondary,
        onBackground = LightTextPrimary,
        onSurface = LightTextPrimary
    )

    val DarkColorScheme = androidx.compose.material3.darkColorScheme(
        primary = Gold,
        secondary = DarkSecondary,
        background = DarkBackground,
        surface = DarkSurface,
        onPrimary = DarkTextPrimary,
        onSecondary = DarkTextSecondary,
        onBackground = DarkTextPrimary,
        onSurface = DarkTextPrimary
    )
}