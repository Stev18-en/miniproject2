package com.stevenmarchy0013.simukmin.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val GreenLightColorScheme = lightColorScheme(
    primary = Color(0xFF2E7D32),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFC8E6C9),
    onPrimaryContainer = Color(0xFF1B5E20)
)

private val BlueLightColorScheme = lightColorScheme(
    primary = Color(0xFF1565C0),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFBBDEFB),
    onPrimaryContainer = Color(0xFF0D47A1)
)

private val PurpleLightColorScheme = lightColorScheme(
    primary = Color(0xFF6A1B9A),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE1BEE7),
    onPrimaryContainer = Color(0xFF4A148C)
)

private val OrangeLightColorScheme = lightColorScheme(
    primary = Color(0xFFEF6C00),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFCC80),
    onPrimaryContainer = Color(0xFFE65100)
)

private val GreenDarkColorScheme = darkColorScheme(
    primary = Color(0xFF81C784),
    onPrimary = Color.Black
)

private val BlueDarkColorScheme = darkColorScheme(
    primary = Color(0xFF64B5F6),
    onPrimary = Color.Black
)

private val PurpleDarkColorScheme = darkColorScheme(
    primary = Color(0xFFCE93D8),
    onPrimary = Color.Black
)

private val OrangeDarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB74D),
    onPrimary = Color.Black
)

@Composable
fun SiMukminTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    themeColor: Int = 0,
    content: @Composable () -> Unit
) {
    val colorScheme: ColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> {
            when (themeColor) {
                1 -> BlueDarkColorScheme
                2 -> PurpleDarkColorScheme
                3 -> OrangeDarkColorScheme
                else -> GreenDarkColorScheme
            }
        }

        else -> {
            when (themeColor) {
                1 -> BlueLightColorScheme
                2 -> PurpleLightColorScheme
                3 -> OrangeLightColorScheme
                else -> GreenLightColorScheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}