package com.example.studenthomepage.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 🎨 --- Figma Color Palette ---
val GRRVRed = Color(0xFF5E0B0B)        // Deep red
val BackgroundLight = Color(0xFFF2F2F2) // Light gray background
val CardWhite = Color(0xFFFFFFFF)       // White surfaces
val TextBlack = Color(0xFF000000)
val TextGray = Color(0xFF777777)
val TextWhite = Color(0xFFFFFFFF)

@Composable
fun StudentHomePageTheme(content: @Composable () -> Unit) {
    val colorScheme = lightColorScheme(
        primary = GRRVRed,
        secondary = GRRVRed,
        background = BackgroundLight,
        surface = CardWhite,
        onPrimary = TextWhite,
        onBackground = TextBlack,
        onSurface = TextBlack
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
