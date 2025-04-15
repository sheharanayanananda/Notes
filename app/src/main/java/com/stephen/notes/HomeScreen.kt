package com.stephen.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import com.stephen.notes.ui.theme.Black
import com.stephen.notes.ui.theme.poppinsFontFamily

@Composable
fun TopBar() {
    //navigate through screens
    val navController = rememberNavController()

    // Get a greeting based on the current time of day
    val greeting = getGreeting()

    // Column to center and arrange the top bar elements horizontally
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(2f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Box container for the top bar with a vertical gradient background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to Color.White,                      // Solid white at the top (0%)
                            0.7f to Color.White,                      // Solid white up to 90% height
                            1.0f to Color.Transparent                 // Fully transparent at the bottom (100%)
                        )
                    )
                )
                //.background(Color.White)
            ,
        ) {
            // Row is used to lay out the greeting text and the icon button horizontally
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
                    .padding(top = 0.dp), // Adjust top padding to position content within the Box
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Display dynamic greeting text
                Text(
                    text = greeting,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 28.sp,
                    color = Black
                )
                // IconButton to handle the add note action (currently a placeholder)
                IconButton(onClick = { navController.navigate(AddNote) }) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add Note",
                        tint = Black,
                        modifier = Modifier.size(45.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun NotesGrid(notes: List<Note>) {
    var selectedNote by remember { mutableStateOf<Note?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(), // No .padding here
            contentPadding = PaddingValues(
                top = 150.dp,
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
                    onLongPress = {
                        selectedNote = note
                    }
                )
            }
        }

        // Show popup dialog when note is selected
        if (selectedNote != null) {
            DeleteConfirmationPopup(
                onDelete = {
                    // Perform deletion here
                    selectedNote = null
                },
                onCancel = {
                    selectedNote = null
                }
            )
        }
    }
}

// Card representing a note with a title, content, and background color
@Composable
fun NoteCard(
    note: Note,
    onLongPress: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true,
                ambientColor = Black,
                spotColor = Color.Gray
            )
            .background(note.color, RoundedCornerShape(20.dp))
            .padding(18.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onLongPress()
                    }
                )
            }
    ) {
        Text(
            text = note.title,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = Black
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = note.content,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            lineHeight = 18.sp,
            fontSize = 11.sp,
            color = Black
        )
    }
}

// Popup dialog for deleting a note
@Composable
fun DeleteConfirmationPopup(
    onDelete: () -> Unit,
    onCancel: () -> Unit
) {
    Dialog(onDismissRequest = onCancel) {
        Surface(
            shape = RoundedCornerShape(30.dp),
            color = Color.White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Delete Note?",
                    modifier = Modifier.padding(bottom = 18.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Are you sure you want to delete this note? This action cannot be undone.",
                    modifier = Modifier.padding(bottom = 20.dp),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onCancel,
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Black,
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .height(45.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(size = 10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text("Cancel", color = Black)
                    }

                    Button(
                        onClick = onDelete,
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .height(45.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(size = 10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Delete", color = Color.White)
                    }
                }
            }
        }
    }
}