package com.stephen.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.stephen.notes.include.core.NavigationGraph
import com.stephen.notes.ui.theme.NotesTheme
import com.stephen.notes.include.core.SystemBarColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NotesTheme {
                SystemBarColor(
                    statusBarColor = Color.Transparent,
                    navigationBarColor = Color.Transparent,
                    darkIcons = true
                )

                val navController = rememberNavController()

                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationGraph(navController = navController)
                }
            }
        }
    }
}
