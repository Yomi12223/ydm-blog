package com.ydmlabo.markdowneditor.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.isSystemInDarkTheme

// YDM.LABOブログダークテーマ
private val YdmDarkColorScheme = darkColorScheme(
    primary = YdmCyan,
    secondary = YdmAccentBlue,
    tertiary = Pink80,
    background = YdmDarkGray,
    surface = YdmSurfaceGray,
    onPrimary = YdmDarkGray,
    onSecondary = YdmDarkGray,
    onTertiary = YdmDarkGray,
    onBackground = YdmTextWhite,
    onSurface = YdmTextWhite,
)

// ライトテーマ（将来的に使用）
private val YdmLightColorScheme = lightColorScheme(
    primary = YdmAccentBlue,
    secondary = YdmCyan,
    tertiary = Pink40,
    background = YdmTextWhite,
    surface = Color(0xFFF5F5F5),
    onPrimary = YdmTextWhite,
    onSecondary = YdmTextWhite,
    onTertiary = YdmTextWhite,
    onBackground = YdmDarkGray,
    onSurface = YdmDarkGray,
)

@Composable
fun YdmBlogEditorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // デフォルトでダークテーマを使用
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        YdmDarkColorScheme
    } else {
        YdmLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
