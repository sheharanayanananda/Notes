package com.stephen.notes.ui.screens
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.stephen.notes.ui.components.viewNote.NoteDescription
import com.stephen.notes.ui.components.viewNote.NoteHeader
import com.stephen.notes.ui.components.viewNote.NoteOptionsPopup
import com.stephen.notes.ui.theme.*

@Composable
fun ViewNoteScreen(
    onNavigateToHome: () -> Unit
) {
    val availableColors = listOf(PeachySunrise, BlushBloom, LavenderHaze, SkyBliss, MintMeadow, SunbeamGlow)
    var showMenuPopup by remember { mutableStateOf(false) }
    var selectedColor by remember { mutableStateOf(availableColors.getOrNull(3) ?: availableColors.first()) }
    val titleState = remember { mutableStateOf("") }
    val descState = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            NoteHeader(
                title = titleState.value,
                onTitleChange = { titleState.value = it },
                selectedColor = selectedColor,
                onMenuClick = { showMenuPopup = true }
            )

            NoteDescription(
                description = descState.value,
                onDescriptionChange = { descState.value = it },
                backgroundColor = selectedColor
            )
        }

        if (showMenuPopup) {
            NoteOptionsPopup(
                availableColors = availableColors,
                selectedColor = selectedColor,
                onColorSelected = { selectedColor = it },
                onCancel = { showMenuPopup = false },
                onSave = {
                    showMenuPopup = false
                    onNavigateToHome()
                }
            )
        }
    }
}