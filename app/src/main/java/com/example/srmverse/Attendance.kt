package com.yourapp.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.draw.clip

@Composable
fun AttendanceScreen(isDark: Boolean) {

    val isDark = isSystemInDarkTheme()

    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val cardColor = if (isDark) Color(0xFF121821) else Color.White
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val chipBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)
    val selectedColor = Color(0xFF00E5FF)

    var selectedTop by remember { mutableStateOf("All") }
    var selectedStatus by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(16.dp)
    ) {

        // 🔹 HEADER (FIXED)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text("Attendance", color = textPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Track your classes", color = textSecondary, fontSize = 12.sp)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Notifications, null, tint = textPrimary)
                Spacer(Modifier.width(14.dp))
                Icon(Icons.Default.Settings, null, tint = textPrimary)
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 FIXED TEXT
        Text(
            "9 subjects tracked",
            color = Color(0xFF00CFE8),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(16.dp))

        // 🔹 TOP CHIP CONTAINER
        Row(
            modifier = Modifier
                .background(chipBg, RoundedCornerShape(30.dp))
                .padding(6.dp)
        ) {

            listOf("All", "Theory", "Practical", "Predict").forEach {

                val bg by animateColorAsState(
                    if (selectedTop == it) selectedColor else Color.Transparent
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(bg)
                        .clickable { selectedTop = it }
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        it,
                        fontSize = 12.sp,
                        color = if (selectedTop == it) Color.Black else textSecondary
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 SEARCH (FIXED STYLE)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    "Search by code, subject or faculty",
                    fontSize = 13.sp,
                    color = textSecondary
                )
            },
            leadingIcon = { Icon(Icons.Default.Search, null) },
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = chipBg,
                focusedContainerColor = chipBg,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // 🔹 STATUS CHIP CONTAINER
        Row(
            modifier = Modifier
                .background(chipBg, RoundedCornerShape(30.dp))
                .padding(6.dp)
        ) {

            listOf("All", "Safe", "Low", "Critical").forEach {

                val bg by animateColorAsState(
                    if (selectedStatus == it) selectedColor else Color.Transparent
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(bg)
                        .clickable { selectedStatus = it }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        it,
                        fontSize = 12.sp,
                        color = if (selectedStatus == it) Color.Black else textSecondary
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 LIST
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            items(5) { index ->

                val data = listOf(
                    Triple("Advanced Calculus and Complex Analysis", 76, "WARNING"),
                    Triple("Semiconductor Physics and Computational Methods", 84, "SAFE"),
                    Triple("Object Oriented Design and Programming", 77, "WARNING"),
                    Triple("Engineering Graphics and Design", 80, "WARNING"),
                    Triple("Environmental Science", 73, "CRITICAL")
                )[index]

                val color = when (data.third) {
                    "SAFE" -> Color(0xFF00E676)
                    "WARNING" -> Color(0xFFFFA000)
                    "CRITICAL" -> Color(0xFFFF5252)
                    else -> Color.Gray
                }

                Card(
                    colors = CardDefaults.cardColors(containerColor = cardColor),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawLine(
                                color = color,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height),
                                strokeWidth = 6f
                            )
                        }
                ) {

                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 6.dp) // 🔥 FIX overlap
                        ) {

                            Text(data.first, color = textPrimary, fontSize = 14.sp)

                            Spacer(Modifier.height(6.dp))

                            Text("Skip 2", color = color, fontSize = 13.sp)
                        }

                        Box(contentAlignment = Alignment.Center) {

                            Canvas(modifier = Modifier.size(50.dp)) {

                                drawArc(
                                    color = color.copy(alpha = 0.2f),
                                    startAngle = 0f,
                                    sweepAngle = 360f,
                                    useCenter = false,
                                    style = Stroke(8f)
                                )

                                drawArc(
                                    color = color,
                                    startAngle = -90f,
                                    sweepAngle = (data.second / 100f) * 360f,
                                    useCenter = false,
                                    style = Stroke(8f)
                                )
                            }

                            Text("${data.second}%", color = color, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}