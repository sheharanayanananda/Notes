package com.stephen.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.stephen.notes.ui.theme.Black
import com.stephen.notes.ui.theme.Blue
import com.stephen.notes.ui.theme.Green
import com.stephen.notes.ui.theme.NotesTheme
import com.stephen.notes.ui.theme.Orange
import com.stephen.notes.ui.theme.Pink
import com.stephen.notes.ui.theme.poppinsFontFamily

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
            Note(1, title, description, Blue),
            Note(2, title, description, Pink),
            Note(3, title, description, Green),
            Note(4, title, description, Orange)
        )

        // Set the content of the activity using your custom theme
        setContent {
            NotesTheme {
                // Surface fills the entire screen with a white background
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    // Column to arrange TopBar and NotesGrid vertically
                    Column {
                        // TopBar displays the greeting and an add note icon
                        TopBar()
                        // Insert some space between the top bar and the grid if needed
                        Spacer(modifier = Modifier.height(30.dp))
                        // NotesGrid displays the sample notes in a 2-column grid layout
                        NotesGrid(notes = sampleNotes)
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    // Get a greeting based on the current time of day
    val greeting = getGreeting()

    // Column to center and arrange the top bar elements horizontally
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Box container for the top bar with a vertical gradient background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp) // Set height of top bar
                .background(Color.White)
        ) {
            // Row is used to lay out the greeting text and the icon button horizontally
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
                    .padding(top = 50.dp), // Adjust top padding to position content within the Box
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
                IconButton(onClick = { /* Handle add note action */ }) {
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
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(33.dp),
            verticalArrangement = Arrangement.spacedBy(33.dp)
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