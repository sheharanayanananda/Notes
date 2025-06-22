package com.stephen.notes.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.stephen.notes.data.model.Note
import com.stephen.notes.ui.components.home.DeletePopup
import com.stephen.notes.ui.components.home.NotesGrid
import com.stephen.notes.ui.components.home.TopBar

@Composable
fun HomeScreen(
    notes: List<Note>,
    onNavigateToViewNote: () -> Unit
) {
    var selectedNote by remember { mutableStateOf<Note?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            TopBar(onNavigateToViewNote = onNavigateToViewNote)
            NotesGrid(
                notes = notes,
                onNoteLongPress = { note: Note -> selectedNote = note }
            )
        }

        if (selectedNote != null) {
            DeletePopup(
                onDelete = {
                    // TODO: Handle actual deletion
                    selectedNote = null
                },
                onCancel = { selectedNote = null }
            )
        }
    }
}
