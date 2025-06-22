package com.stephen.notes.include.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stephen.notes.ui.screens.HomeScreen
import com.stephen.notes.data.model.Note
import com.stephen.notes.ui.screens.ViewNoteScreen
import com.stephen.notes.ui.theme.*
import kotlinx.serialization.Serializable

@Composable
fun NavigationGraph(navController: NavHostController) {
    // Sample list of notes to populate the grid
    val sampleNotes = listOf(
        Note(1,
            "Grocery List",
            "Milk, eggs, bread, and fresh fruits. Don't forget almond butter and spinach for smoothies.",
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
            "'Atomic Habits' by James Clear; 'Clean Code' by Robert C. Martin; 'Deep Work' by Cal Newport.",
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
            "Scented candles for Mom; wireless mouse for Dad; sketchbook for my sister's art hobby.",
            SunbeamGlow),
        Note(12,
            "Inspirational Quotes",
            "\"The only way to do great work is to love what you do.\" — Steve Jobs",
            LavenderHaze)
    )

    NavHost(navController = navController, startDestination = Screen.HomeScreen) {
        composable<Screen.HomeScreen> {
            HomeScreen(
                notes = sampleNotes, // Now using the populated sample notes
                onNavigateToViewNote = { navController.navigate(Screen.ViewNoteScreen) }
            )
        }
        composable<Screen.ViewNoteScreen> {
            ViewNoteScreen(
                onNavigateToHome = { navController.navigate(Screen.HomeScreen) }
            )
        }
    }
}

object Screen {
    @Serializable
    object HomeScreen

    @Serializable
    object ViewNoteScreen
}