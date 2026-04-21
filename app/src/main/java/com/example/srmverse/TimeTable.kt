package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewQuilt
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimetableScreen(isDark: Boolean) {

    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val accentColor = Color(0xFF00CFE8) // More vibrant teal/cyan from screenshot
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)

    var selectedDay by remember { mutableStateOf("D3") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        // 🔹 HEADER
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isDark) Color(0xFF162638) else Color.White
            ),
            elevation = CardDefaults.cardElevation(if (isDark) 4.dp else 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .border(
                                1.dp,
                                Color(0xFF00BFA6).copy(alpha = 0.5f),
                                RoundedCornerShape(10.dp)
                            )
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ViewQuilt,
                            contentDescription = null,
                            tint = Color(0xFF00BFA6),
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    Column {
                        Text(
                            "Timetable",
                            color = textPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Your weekly schedule",
                            color = textSecondary,
                            fontSize = 12.sp
                        )
                    }
                }

                Row {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Notifications,
                            null,
                            tint = textSecondary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Settings,
                            null,
                            tint = textSecondary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // 🔹 SUMMARY ROW
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color(0xFF00BFA6), fontWeight = FontWeight.Bold)) {
                            append("4 ")
                        }
                        withStyle(style = SpanStyle(color = textSecondary)) {
                            append("classes on Day 3")
                        }
                    },
                    fontSize = 14.sp
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(32.dp)
                    .background(chipContainerBg, CircleShape)
            ) {
                Icon(
                    Icons.Outlined.Refresh,
                    null,
                    tint = accentColor,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 DOWNLOAD & TODAY BADGE
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = { },
                border = BorderStroke(1.dp, Color(0xFF00BFA6).copy(alpha = 0.6f)),
                shape = RoundedCornerShape(30.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
                modifier = Modifier.height(36.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF00BFA6))
            ) {
                Icon(Icons.Outlined.FileDownload, null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
                Text("Download PDF", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            Surface(
                color = if (isDark) Color(0xFF1A222B) else Color(0xFFE0E0E0),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        "TODAY: D2",
                        color = Color(0xFF00BFA6),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 DAY CHIPS
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("D1", "D2", "D3", "D4", "D5").forEach { day ->
                val isSelected = selectedDay == day
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(30.dp))
                        .background(if (isSelected) Color(0xFF1A222B) else Color.Transparent)
                        .clickable { selectedDay = day }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = day,
                        color = if (isSelected) Color(0xFF00BFA6) else textSecondary,
                        fontSize = 14.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 SEARCH BAR
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text("Search by subject, faculty or room", color = textSecondary, fontSize = 14.sp)
            },
            leadingIcon = {
                Icon(Icons.Default.Search, null, tint = textSecondary)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = chipContainerBg,
                focusedContainerColor = chipContainerBg,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = accentColor
            )
        )

        Spacer(Modifier.height(16.dp))

        // 🔹 TIMETABLE LIST
        val timetableData = listOf(
            TimetableItem("Electrical and Electronics Engineering", "Dr.Y.Jeyashree", "Room 822", "08:00", "09:40", 78, "40/51"),
            TimetableItem("Semiconductor Physics and Computational Methods", "Dr. Tusharbhai Himmatbhai Rana", "Room 822", "10:40", "11:30", 84, "38/45"),
            TimetableItem("Advanced Calculus and Complex Analysis", "Dr. D.K.Sheena Christy", "Room 822", "11:35", "12:25", 76, "39/51"),
            TimetableItem("Engineering Graphics and Design", "Dr. Vamsi Krishna Dommeti", "Room BEL 508, Basic Engineering Lab", "13:25", "16:50", 80, "35/44")
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            items(timetableData) { item ->
                TimetableCard(item, isDark)
            }
        }
    }
}

data class TimetableItem(
    val name: String,
    val faculty: String,
    val room: String,
    val startTime: String,
    val endTime: String,
    val percentage: Int,
    val fraction: String
)

@Composable
fun TimetableCard(item: TimetableItem, isDark: Boolean) {
    val accentColor = Color(0xFF00BFA6)
    val cardColor = if (isDark) Color(0xFF111821) else Color.White
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .drawBehind {
                drawLine(
                    color = accentColor.copy(alpha = 0.5f),
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 10f
                )
            },
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Time Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(55.dp)
            ) {
                Icon(Icons.Outlined.Schedule, null, tint = accentColor, modifier = Modifier.size(16.dp))
                Spacer(Modifier.height(6.dp))
                Text(item.startTime, color = textPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text("to", color = textSecondary, fontSize = 10.sp)
                Text(item.endTime, color = textPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.width(12.dp))

            // Vertical Divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(70.dp)
                    .background(Color.Gray.copy(alpha = 0.15f))
            )

            Spacer(Modifier.width(16.dp))

            // Info Section
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    color = textPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp
                )

                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, null, tint = textSecondary, modifier = Modifier.size(13.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(item.faculty, color = textSecondary, fontSize = 12.sp)
                }

                Spacer(Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.Top) {
                    Icon(
                        Icons.Default.LocationOn,
                        null,
                        tint = textSecondary,
                        modifier = Modifier
                            .size(13.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(item.room, color = textSecondary, fontSize = 12.sp, lineHeight = 16.sp)
                }
            }

            Spacer(Modifier.width(12.dp))

            // Progress Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(contentAlignment = Alignment.Center) {
                    val progressColor = when {
                        item.percentage >= 84 -> Color(0xFF00E676) // Green
                        item.percentage >= 75 -> Color(0xFFFFA000) // Orange
                        else -> Color(0xFFFF5252) // Red
                    }

                    Canvas(modifier = Modifier.size(50.dp)) {
                        drawArc(
                            color = progressColor.copy(alpha = 0.1f),
                            startAngle = 0f,
                            sweepAngle = 360f,
                            useCenter = false,
                            style = Stroke(width = 6f)
                        )
                        drawArc(
                            color = progressColor,
                            startAngle = -90f,
                            sweepAngle = (item.percentage / 100f) * 360f,
                            useCenter = false,
                            style = Stroke(width = 6f, cap = StrokeCap.Round)
                        )
                    }
                    Text(
                        "${item.percentage}%",
                        color = progressColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    item.fraction,
                    color = textSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
