package com.stephen.notes.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.stephen.notes.include.misc.getGreeting
import com.stephen.notes.ui.theme.Black
import com.stephen.notes.ui.theme.poppinsFontFamily

@Composable
fun TopBar(onNavigateToViewNote: () -> Unit) {
    val greeting = getGreeting()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(2f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to Color.White,
                            0.5f to Color.White,
                            0.6f to Color.White.copy(alpha = 0.9f),
                            0.7f to Color.White.copy(alpha = 0.7f),
                            0.8f to Color.White.copy(alpha = 0.4f),
                            0.9f to Color.White.copy(alpha = 0.2f),
                            1.0f to Color.Transparent
                        )
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, end = 20.dp, top = 20.dp),
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
                IconButton(onClick = onNavigateToViewNote) {
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