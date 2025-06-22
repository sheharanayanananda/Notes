package com.stephen.notes.ui.components.viewNote

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stephen.notes.ui.theme.Black
import com.stephen.notes.ui.theme.poppinsFontFamily

@Composable
fun NoteHeader(
    title: String,
    onTitleChange: (String) -> Unit,
    selectedColor: Color,
    onMenuClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(selectedColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 20.dp, top = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .horizontalScroll(scrollState)
            ) {
                TextField(
                    value = title,
                    onValueChange = onTitleChange,
                    singleLine = true,
                    maxLines = 1,
                    placeholder = {
                        Text(
                            "My Todo List",
                            fontFamily = poppinsFontFamily,
                            fontSize = 22.sp,
                            color = Black.copy(alpha = 0.5f)
                        )
                    },
                    textStyle = TextStyle(
                        fontFamily = poppinsFontFamily,
                        fontSize = 25.sp,
                        color = Black
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Black,
                        unfocusedTextColor = Color.Gray,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "More Options",
                    tint = Black,
                    modifier = Modifier.size(40.dp),
                )
            }
        }
    }
}