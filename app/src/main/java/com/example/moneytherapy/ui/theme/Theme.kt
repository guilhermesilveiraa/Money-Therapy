package com.example.moneytherapy.ui.theme

import BackgroundDark
import BackgroundPrimary
import BackgroundSecondary
import PrimaryAccent
import PrimaryDark
import PrimaryLight
import TextLight
import TextPrimary
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// In ui/theme/Theme.kt

private val LightColorScheme = lightColorScheme(
    primary = PrimaryDark,
    secondary = PrimaryLight,
    tertiary = PrimaryAccent,
    background = BackgroundPrimary,
    surface = BackgroundSecondary,
    onPrimary = TextLight,
    onSecondary = TextLight,
    onTertiary = TextPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryLight,
    secondary = PrimaryDark,
    tertiary = PrimaryAccent,
    background = BackgroundDark,
    surface = BackgroundDark.copy(alpha = 0.7f),
    onPrimary = TextLight,
    onSecondary = TextLight,
    onBackground = TextLight,
    onSurface = TextLight
)

@Composable
fun MoneyTherapyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}