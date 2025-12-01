package com.example.deferalapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColors = darkColorScheme(
    primary = Maroon,
    onPrimary = Color.White,
    background = Cream,
    surface = CardGrey,
    onBackground = TextBlack,
    onSurface = TextBlack
)

@Composable
fun DeferalTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColors,
        content = content
    )
}
