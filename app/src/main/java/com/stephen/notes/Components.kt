package com.stephen.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.stephen.notes.ui.theme.Black
import com.stephen.notes.ui.theme.poppinsFontFamily

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
            .shadow(3.dp, RoundedCornerShape(20.dp))
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