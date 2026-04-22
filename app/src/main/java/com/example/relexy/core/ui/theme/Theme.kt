package com.example.relexy.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
)

private val LightColorScheme = lightColorScheme(
    // Главный акцент приложения
    primary = RelexyAccent,
    onPrimary = RelexyTextOnColor,

    // Доп. акцент
    secondary = RelexyBtn,
    onSecondary = RelexyTextOnColor,

    // Фоны
    background = RelexyBg,
    onBackground = RelexySubtitle,

    // Surface - поверхности
    surface = RelexyCard,
    onSurface = RelexyTextMain,

    // Для вариантов surface
    surfaceVariant = RelexyAccentBg,
    onSurfaceVariant = RelexyTitle,

    // Ошибки
    error = RelexyError,
    onError = RelexyTextOnColor,

)

@Composable
fun RelexyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> LightColorScheme /*DarkColorScheme*/
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}