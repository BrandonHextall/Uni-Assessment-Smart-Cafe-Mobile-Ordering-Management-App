package com.example.scoa.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

// Checks if the user's device is in dark mode.
// Will be used to change the colour scheme of the app.
@Composable
fun isDarkModeEnabled(): Boolean {
    return isSystemInDarkTheme()
}