package com.example.scoa.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Custom colour scheme
private val CustomTheme = darkColorScheme(
    primary = caramel,
    onPrimary = textDark,

    secondary = lightCaramel,
    onSecondary = textLight,

    background = maroon,
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