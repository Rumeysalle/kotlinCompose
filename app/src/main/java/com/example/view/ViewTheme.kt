package com.example.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.view.ui.theme.AppTypography

@Composable
fun ViewTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF263238),
            secondary = Color(0xFF2E7D32),
            tertiary = Color(0xFF2E7D32),
        ),
        typography = AppTypography
    )
    {
        content()

    }
}