package com.stephen.notes.ui.components.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.stephen.notes.ui.theme.Black
import com.stephen.notes.ui.theme.poppinsFontFamily

@Composable
fun DeletePopup(
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
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Delete Note?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Are you sure you want to delete this note? This action cannot be undone.",
                    color = Black,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onCancel,
                        modifier = Modifier
                            .border(2.dp, Black, RoundedCornerShape(15.dp))
                            .height(48.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text("Cancel", color = Black, fontFamily = poppinsFontFamily, fontSize = 15.sp)
                    }

                    Button(
                        onClick = onDelete,
                        modifier = Modifier
                            .border(2.dp, Black, RoundedCornerShape(15.dp))
                            .height(48.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Black)
                    ) {
                        Text("Delete", color = Color.White, fontFamily = poppinsFontFamily, fontSize = 15.sp)
                    }
                }
            }
        }
    }
}