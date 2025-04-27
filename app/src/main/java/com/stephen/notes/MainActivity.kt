package com.stephen.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.stephen.notes.ui.theme.BlushBloom
import com.stephen.notes.ui.theme.LavenderHaze
import com.stephen.notes.ui.theme.MintMeadow
import com.stephen.notes.ui.theme.NotesTheme
import com.stephen.notes.ui.theme.PeachySunrise
import com.stephen.notes.ui.theme.SkyBliss
import com.stephen.notes.ui.theme.SunbeamGlow
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables drawing content edge-to-edge (allows the content to appear behind system bars)
        enableEdgeToEdge()

        // Sample list of notes to populate the grid
        val title = "Note Title"
        val description = "While this approach works, it's not an ideal way to " +
                "architect your app. A benefit of using a NavHost to handle your" +
                " app's navigation is that navigation logic is kept separate from individual UI."

        val sampleNotes = listOf(
            Note(1, title, description, PeachySunrise),  // PeachySunrise
            Note(2, title, description, SkyBliss),       // SkyBliss
            Note(3, title, description, BlushBloom),     // BlushBloom
            Note(4, title, description, MintMeadow),     // MintMeadow
            Note(5, title, description, SunbeamGlow),    // SunbeamGlow
            Note(6, title, description, LavenderHaze),   // LavenderHaze
            Note(1, title, description, PeachySunrise),  // PeachySunrise
            Note(2, title, description, SkyBliss),       // SkyBliss
            Note(3, title, description, BlushBloom),     // BlushBloom
            Note(4, title, description, MintMeadow),     // MintMeadow
            Note(5, title, description, SunbeamGlow),    // SunbeamGlow
            Note(6, title, description, LavenderHaze),   // LavenderHaze
        )

        setContent {
            NotesTheme {
                val navController = rememberNavController()

                SystemBarColor(
                    statusBarColor = Color.Transparent,
                    navigationBarColor = Color.Transparent,
                    darkIcons = true // This makes status bar icons dark (for light themes)
                )

                NavHost(
                    navController = navController,
                    startDestination = Home
                ) {
                    composable<Home> {
                        // Surface fills the entire screen with a white background
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color.White
                        ) {
                            TopBar(
                                onNavigateToAddNote = { navController.navigate(AddNote) }
                            )
                            NotesGrid(notes = sampleNotes)
                        }
                    }
                    composable<AddNote> {
                        // Surface fills the entire screen with a white background
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color.White
                        ) {
                            Header(
                                onNavigateToHome = { navController.navigate(Home) }
                            )
                        }
                    }
                }
            }
        }
    }
}

// Create a composable for system bar configuration
@Composable
fun SystemBarColor(
    statusBarColor: Color = Color.Transparent,
    navigationBarColor: Color = Color.Transparent,
    darkIcons: Boolean = true
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = darkIcons
        )

        systemUiController.setNavigationBarColor(
            color = navigationBarColor,
            darkIcons = darkIcons
        )
    }
}

@Serializable
object Home

@Serializable
object AddNote