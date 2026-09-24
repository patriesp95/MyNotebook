package com.patrimesp.mynotebook.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.patrimesp.mynotebook.R

@Composable
fun MyNotebookTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = darkColorScheme(
        primary = colorResource(R.color.purple_500),
        onPrimary = colorResource(R.color.white),
        secondary = colorResource(R.color.purple_200),
        onSecondary = colorResource(R.color.black),
        tertiary = colorResource(R.color.teal_200),
        onTertiary = colorResource(R.color.black),
        background = colorResource(R.color.purple_700),
        onBackground = colorResource(R.color.purple_100),
        surface = colorResource(R.color.purple_700),
        onSurface = colorResource(R.color.white),
        surfaceVariant = colorResource(R.color.purple_500),
        onSurfaceVariant = colorResource(R.color.white),
        outline = colorResource(R.color.purple_200)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
