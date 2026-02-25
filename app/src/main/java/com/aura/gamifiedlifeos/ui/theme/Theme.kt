package com.aura.gamifiedlifeos.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val GentleCyberpunkScheme = darkColorScheme(
    primary = SoftNeonBlue,
    secondary = SoftNeonPurple,
    tertiary = SoftNeonMint,
    background = CyberNight,
    surface = SurfaceDark
)

@Composable
fun GamifiedLifeOSTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = GentleCyberpunkScheme,
        content = content
    )
}
