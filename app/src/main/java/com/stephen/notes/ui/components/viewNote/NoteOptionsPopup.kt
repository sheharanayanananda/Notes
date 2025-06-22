package com.stephen.notes.ui.components.viewNote

import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.stephen.notes.ui.theme.Black
import com.stephen.notes.ui.theme.poppinsFontFamily

@Composable
fun NoteOptionsPopup(
    availableColors: List<Color>,
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    onCancel: () -> Unit,
    onSave: () -> Unit
) {
    // Optional: animate appearance
    val scale = remember { androidx.compose.animation.core.Animatable(0.8f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = 0.5f,
                stiffness = 400f
            )
        )
    }

    Popup(
        alignment = Alignment.BottomCenter,
        onDismissRequest = onCancel,
        properties = PopupProperties(focusable = true)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                    alpha = scale.value
                }
        ) {
            Surface(
                shape = RoundedCornerShape(30.dp),
                color = Color.White,
                tonalElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(6.dp, RoundedCornerShape(30.dp)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Section Title
                    Text(
                        text = "Color Theme",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = Black
                    )

                    // Color selection row
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        availableColors.forEach { color ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .clip(CircleShape)
                                    .background(color)
                                    .border(
                                        width = if (color == selectedColor) 3.dp else 1.dp,
                                        color = if (color == selectedColor) Black else Color.LightGray,
                                        shape = CircleShape
                                    )
                                    .clickable { onColorSelected(color) }
                            )
                        }
                    }

                    Spacer(Modifier.height(10.dp))

                    // Confirmation heading
                    Text(
                        text = "Everything's Done?",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = Black
                    )

                    // Info description
                    Text(
                        text = "Consider saving before you exit, because you might lose what you’ve done here. " +
                                "Also everything will be saved locally so no need to worry about network connections next time.",
                        fontFamily = poppinsFontFamily,
                        fontSize = 13.sp,
                        lineHeight = 23.sp,
                        color = Black
                    )

                    Spacer(Modifier.height(2.dp))

                    // Action buttons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = onCancel,
                            modifier = Modifier
                                .height(48.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(15.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            border = BorderStroke(2.dp, Black)
                        ) {
                            Text(
                                "Cancel",
                                color = Black,
                                fontFamily = poppinsFontFamily,
                                fontSize = 15.sp,
                            )
                        }

                        Button(
                            onClick = onSave,
                            modifier = Modifier
                                .height(48.dp)
                                .weight(2f),
                            shape = RoundedCornerShape(15.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Black)
                        ) {
                            Text(
                                "Save",
                                color = Color.White,
                                fontFamily = poppinsFontFamily,
                                fontSize = 15.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}