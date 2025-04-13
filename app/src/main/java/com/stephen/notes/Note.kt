package com.stephen.notes

import androidx.compose.ui.graphics.Color

// Data class representing a Note object that holds an id, title, content, and background color.
data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val color: Color,
)
