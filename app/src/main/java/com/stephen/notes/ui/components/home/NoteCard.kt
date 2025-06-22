package com.stephen.notes.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stephen.notes.data.model.Note
import com.stephen.notes.ui.theme.Black
import com.stephen.notes.ui.theme.poppinsFontFamily

@Composable
fun NoteCard(
    note: Note,
    onLongPress: () -> Unit
) {
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
            .padding(15.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = { onLongPress() })
            }
    ) {
        Text(
            text = note.title,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.5.sp,
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