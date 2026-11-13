package com.example.scoa.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.scoa.R

// Custom font
val pinyon = FontFamily (
    Font (
        resId = R.font.pinyon_script_regular,
        weight = FontWeight.Normal
    )
)
// Defines app's typography
val typography = Typography (
    titleLarge = TextStyle (
        fontFamily = pinyon,
        fontWeight = FontWeight.Bold,
        fontSize = 55.sp
    ),
    // Large heading
    headlineLarge = TextStyle(
        fontFamily = pinyon,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    // Small heading
    headlineSmall = TextStyle (
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    // Normal text
    bodyLarge = TextStyle (
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)