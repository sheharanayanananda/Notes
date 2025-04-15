package com.stephen.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stephen.notes.ui.theme.BlushBloom
import com.stephen.notes.ui.theme.CloudWhisper
import com.stephen.notes.ui.theme.FrostyMist
import com.stephen.notes.ui.theme.LavenderHaze
import com.stephen.notes.ui.theme.MintMeadow
import com.stephen.notes.ui.theme.NotesTheme
import com.stephen.notes.ui.theme.PeachySunrise
import com.stephen.notes.ui.theme.RoseCoral
import com.stephen.notes.ui.theme.SkyBliss
import com.stephen.notes.ui.theme.SunbeamGlow
import com.stephen.notes.ui.theme.VanillaCream
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables drawing content edge-to-edge (allows the content to appear behind system bars)
        enableEdgeToEdge()

        // Set status bar to white and make the icons dark (suitable for a light theme)
        window.statusBarColor = android.graphics.Color.WHITE
        WindowCompat.getInsetsController(window, window.decorView)?.isAppearanceLightStatusBars = true

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
            Note(7, title, description, FrostyMist),     // FrostyMist
            Note(8, title, description, RoseCoral),      // RoseCoral
            Note(9, title, description, VanillaCream),   // VanillaCream
            Note(10, title, description, CloudWhisper)   // CloudWhisper
        )

        setContent {
            NotesTheme {
                val navController = rememberNavController()
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
                            TopBar()
                            NotesGrid(notes = sampleNotes)
                        }
                    }
                    composable<AddNote> {
                        // Surface fills the entire screen with a white background
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color.White
                        ) {
                            Header()
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object Home

@Serializable
object AddNote