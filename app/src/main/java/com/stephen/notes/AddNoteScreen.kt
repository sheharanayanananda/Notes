package com.stephen.notes

import android.util.EventLogTags
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.stephen.notes.ui.theme.*

@Composable
fun Header(
    onNavigateToHome: () -> Unit,
) {
    // 1. Colors available for the popup
    val availableColors = listOf(
        PeachySunrise, BlushBloom, LavenderHaze,
        SkyBliss, MintMeadow, SunbeamGlow
    )

    // 2. State for popup visibility & selected color
    var showMenuPopup by remember { mutableStateOf(false) }       // toggle popup :contentReference[oaicite:4]{index=4}
    var selectedColor by remember { mutableStateOf(availableColors.first()) }

    // 5. Title input + menu button
    val titleState = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    // 3. Root container filling the whole screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f),
    ) {
        // 4. Header UI (fixed height, gradient background)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(2f),  // keep on top
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0f to selectedColor,
                                0.7f to selectedColor,
                                1.0f to Color.Transparent
                            )
                        )
                    )
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .horizontalScroll(scrollState)
                    ) {
                        TextField(
                            value = titleState.value,
                            onValueChange = { titleState.value = it },
                            singleLine = true,
                            maxLines = 1,
                            placeholder = {
                                Text(
                                    "Title is here",
                                    fontFamily = poppinsFontFamily,
                                    fontSize = 22.sp,
                                    color = Black,
                                    textAlign = TextAlign.Start
                                )
                            },
                            textStyle = TextStyle(
                                fontFamily = poppinsFontFamily,
                                fontSize = 25.sp,
                                color = Black,
                                textAlign = TextAlign.Start
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

                    IconButton(onClick = { showMenuPopup = true }) {
                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = "More Options",
                            tint = Black,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        }

        // 6. Bottom-aligned, full-width menu popup
        if (showMenuPopup) {
            Popup(
                alignment = Alignment.BottomCenter,
                properties = PopupProperties(
                    focusable = true,
                    dismissOnClickOutside = true
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(30.dp),
                        color = Color.White,
                        tonalElevation = 8.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(elevation = 6.dp, shape = RoundedCornerShape(30.dp)),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            // Color Theme header
                            Text(
                                "Color Theme",
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp,
                                color = Black
                            )

                            // Dynamic row of color circles
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                availableColors.forEach { color ->
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)                                 // distributes width evenly
                                            .aspectRatio(1f)                            // keeps it square
                                            .clip(CircleShape)                          // clip to circle shape
                                            .background(color, CircleShape)
                                            .border(
                                                width = if (color == selectedColor) 3.dp else 1.dp,
                                                color = if (color == selectedColor) Black else Color.LightGray,
                                                shape = CircleShape
                                            )
                                            .clickable { selectedColor = color }
                                    )
                                }
                            }

                            Spacer(Modifier.height(10.dp))

                            // Confirmation header
                            Text(
                                "Everything's Done?",
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp,
                                color = Black
                            )

                            // Body description
                            Text(
                                "Consider saving before you exit, because you might lose what you’ve done here. " +
                                        "Also everything will be saved locally so no need to worry about network connections next time.",
                                fontFamily = poppinsFontFamily,
                                fontSize = 14.sp,
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
                                    onClick = { showMenuPopup = false },
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
                                    onClick = {
                                        // TODO: save with selectedColor
                                        showMenuPopup = false
                                    },
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
    }

    val descState = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(selectedColor)
            .padding(
                top = 120.dp,
                start = 30.dp,
                end = 30.dp,
                bottom = 30.dp
            ),
    ) {
        TextField(
            value = descState.value,
            onValueChange = { descState.value = it },
            placeholder = {
                Text(
                    "Type here something...",
                    fontFamily = poppinsFontFamily,
                    fontSize = 16.sp,
                    color = Black,
                    textAlign = TextAlign.Start
                )
            },
            textStyle = TextStyle(
                fontFamily = poppinsFontFamily,
                fontSize = 16.sp,
                color = Black,
                textAlign = TextAlign.Start
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