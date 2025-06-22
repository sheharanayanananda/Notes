package com.stephen.notes.include.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemBarColor(
    statusBarColor: Color = Color.Transparent,
    navigationBarColor: Color = Color.Transparent,
    darkIcons: Boolean = true
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons)
        systemUiController.setNavigationBarColor(navigationBarColor, darkIcons)
    }
}