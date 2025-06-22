package com.stephen.notes.data.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Entity representing a Note.
 * Stores title, content, and a color in hex format.
 */

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String = "",
    val content: String = "",
    val color: Color,
)