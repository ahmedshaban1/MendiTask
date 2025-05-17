package com.mendi.task.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = Color(0xFFFFFFFF),
    onPrimary = Color(0xFF28333E),
    surface = Color(0xFF141B21),
    surfaceContainer = Color(0xFF1B232B),
    surfaceContainerHigh = Color(0xFF26303B),
    tertiary = Color(0xFFFF8049),
    onSurface = Color(0xFFFFFFFF),
    onSurfaceVariant = Color(0xFFA7AEB7),
  )

private val LightColorScheme =
  lightColorScheme(
    primary = Color(0xFF28333E),
    onPrimary = Color(0xFFFFFFFF),
    surface = Color(0xFFFBFBFB),
    surfaceContainer = Color(0xFFEFF1F3),
    surfaceContainerHigh = Color(0xFFE3E8EC),
    tertiary = Color(0xFFE26600),
    onSurface = Color(0xFF28333E),
    onSurfaceVariant = Color(0xFF6C7580),
  )

@Composable
fun MendiTaskTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  CompositionLocalProvider(LocalSpacing provides Spacing()) {
    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content,
    )
  }
}
