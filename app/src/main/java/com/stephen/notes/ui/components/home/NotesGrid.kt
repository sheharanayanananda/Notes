package com.stephen.notes.ui.components.home

import com.stephen.notes.data.model.Note
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotesGrid(
    notes: List<Note>,
    onNoteLongPress: (Note) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = 140.dp,
            start = 30.dp,
            end = 30.dp,
            bottom = 30.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(notes) { note ->
            NoteCard(
                note = note,
                onLongPress = { onNoteLongPress(note) }
            )
        }
    }
}