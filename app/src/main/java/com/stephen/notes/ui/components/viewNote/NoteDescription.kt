package com.stephen.notes.ui.components.viewNote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stephen.notes.ui.theme.Black
import com.stephen.notes.ui.theme.poppinsFontFamily

@Composable
fun NoteDescription(
    description: String,
    onDescriptionChange: (String) -> Unit,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(start = 15.dp, end = 15.dp, top = 0.dp, bottom = 15.dp)
    ) {
        TextField(
            value = description,
            onValueChange = onDescriptionChange,
            placeholder = {
                Text(
                    """
                        Start typing your thoughts...
                        Grocery List
                        Brilliant Ideas
                    """.trimIndent(),
                    fontFamily = poppinsFontFamily,
                    fontSize = 17.sp,
                    lineHeight = 34.sp,
                    color = Black.copy(alpha = 0.5f)
                )
            },
            textStyle = TextStyle(
                fontFamily = poppinsFontFamily,
                fontSize = 17.sp,
                lineHeight = 34.sp,
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
}