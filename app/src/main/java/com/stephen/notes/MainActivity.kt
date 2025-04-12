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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stephen.notes.ui.theme.Black
import com.stephen.notes.ui.theme.NotesTheme
import com.stephen.notes.ui.theme.poppinsFontFamily
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import com.stephen.notes.ui.theme.Blue
import com.stephen.notes.ui.theme.Green
import com.stephen.notes.ui.theme.Orange
import com.stephen.notes.ui.theme.Pink

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Sample list of notes
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

        setContent {
            NotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Column {
                        TopBar()
                        NotesGrid(notes = sampleNotes)
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    val greeting = getGreeting()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar container; it's now at the top of the screen because it's the first child in the Column
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White,
                            Color.White.copy(alpha = 0.6f),
                            Color.Transparent
                        ),
                        startY = 60f,
                        endY = 100f
                    )
                ),
//                .background(Color.White),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
                    .padding(top = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = greeting,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 28.sp,
                    color = Black
                )
                IconButton(onClick = { /* Handle add note action */ }) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add Note",
                        tint = Black,
                        modifier = Modifier.size(45.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun NotesGrid(notes: List<Note>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Display two columns
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(33.dp),
        verticalArrangement = Arrangement.spacedBy(33.dp)
    ) {
        // Render each note as a note card
        items(notes) { note ->
            NoteCard(note)
        }
    }
}

@Composable
fun NoteCard(note: Note) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
//            .height(165.dp)
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(20.dp))
            .background(note.color, shape = RoundedCornerShape(20.dp))
            .padding(18.dp)
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

//@Composable
//fun NoteCard(note: Note) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(165.dp)
//            .background(Color(note.color), shape = RoundedCornerShape(20.dp))
//            .padding(18.dp)
//    ) {
//        Text(
//            text = note.title,
//            fontFamily = poppinsFontFamily,
//            fontWeight = FontWeight.Medium,
//            fontSize = 15.sp,
//            color = Black
//        )
//        Spacer(modifier = Modifier.height(6.dp))
//        Text(
//            text = note.content,
//            fontFamily = poppinsFontFamily,
//            fontWeight = FontWeight.Normal,
//            lineHeight = 18.sp,
//            fontSize = 11.sp,
//            color = Black
//        )
//    }
//}

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val color: Color,
)