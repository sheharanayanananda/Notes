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
        // Sample list of notes to populate the grid with unique titles and descriptions
        val sampleNotes = listOf(
            Note(1,
                "Grocery List",
                "Milk, eggs, bread, and fresh fruits. Don’t forget almond butter and spinach for smoothies.",
                PeachySunrise),
            Note(2,
                "Meeting Agenda",
                "Discuss Q2 targets, review team feedback, and plan next sprint tasks. Allocate time for brainstorming.",
                SkyBliss),
            Note(3,
                "Vacation Itinerary",
                "Day 1: Beach sunrise; Day 2: Mountain hike; Day 3: Local market tour and cooking class.",
                BlushBloom),
            Note(4,
                "Project Ideas",
                "Build a weather app using Jetpack Compose; integrate with OpenWeatherMap API and local notifications.",
                MintMeadow),
            Note(5,
                "Workout Routine",
                "Monday: Upper body strength; Wednesday: HIIT cardio; Friday: Yoga and stretching for recovery.",
                SunbeamGlow),
            Note(6,
                "Reading List",
                "‘Atomic Habits’ by James Clear; ‘Clean Code’ by Robert C. Martin; ‘Deep Work’ by Cal Newport.",
                LavenderHaze),
            Note(7,
                "Recipe Corner",
                "Spaghetti Aglio e Olio: Garlic, chili flakes, olive oil, parsley. Ready in 15 minutes!",
                PeachySunrise),
            Note(8,
                "Daily Journal",
                "Grateful for sunny mornings and productive work sessions. Tomorrow: focus on UI polishing.",
                SkyBliss),
            Note(9,
                "Budget Tracker",
                "Allocate ₹10,000 for rent, ₹5,000 for groceries, ₹2,000 for entertainment this month.",
                BlushBloom),
            Note(10,
                "Wish List",
                "New headphones, ergonomic chair, and a Nintendo Switch game for weekend relaxation.",
                MintMeadow),
            Note(11,
                "Birthday Gifts",
                "Scented candles for Mom; wireless mouse for Dad; sketchbook for my sister’s art hobby.",
                SunbeamGlow),
            Note(12,
                "Inspirational Quotes",
                "“The only way to do great work is to love what you do.” — Steve Jobs",
                LavenderHaze)
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
                            AddNote(
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