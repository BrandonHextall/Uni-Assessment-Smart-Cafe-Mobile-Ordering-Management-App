package com.example.scoa.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
fun isDarkModeEnabled(): Boolean {
    return isSystemInDarkTheme()
}