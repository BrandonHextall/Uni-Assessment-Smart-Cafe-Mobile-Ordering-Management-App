package com.example.scoa.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Custom colour scheme
private val CustomTheme = darkColorScheme(
    primary = oliveTurd,
    onPrimary = textDark,

    secondary = beige,
    onSecondary = textLight,

    background = caramel,
    onBackground = textLight,

    surface = Color(0xFF4E342E),
    onSurface = textLight
)

@Composable
fun AppTheme(
    isDark: Boolean = isDarkModeEnabled(),
    content: @Composable () -> Unit
) {
    // Apply theme
    MaterialTheme(
        colorScheme = CustomTheme,
        typography = typography,
        content = content
    )
}