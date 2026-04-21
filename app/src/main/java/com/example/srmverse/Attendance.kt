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
import androidx.compose.material.icons.outlined.Refresh
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
fun AttendanceScreen(isDark: Boolean) {

    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val accentColor = Color(0xFF00E5FF)
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)

    var selectedType by remember { mutableStateOf("All") }
    var selectedStatus by remember { mutableStateOf("All") }

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
                            "Attendance",
                            color = textPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Track your classes",
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

        // 🔹 SEARCH BAR (Now at the top of controls)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text("Search by code, subject or faculty", color = textSecondary, fontSize = 14.sp)
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

        // 🔹 FILTER SECTION (Grouped Chips)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Top Chips (Theory, Practical...)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(chipContainerBg, RoundedCornerShape(30.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("All", "Theory", "Practical", "Predict").forEach { type ->
                    val isSelected = selectedType == type
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { selectedType = type }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = type,
                            color = if (isSelected) textPrimary else textSecondary,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }

            // Status Chips (Safe, Low, Critical)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("All", "Safe", "Low", "Critical").forEach { status ->
                    val isSelected = selectedStatus == status
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) accentColor else chipContainerBg)
                            .clickable { selectedStatus = status }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = status,
                            color = if (isSelected) Color.Black else textSecondary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // 🔹 SUMMARY ROW (Now neatly placed above the list)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(accentColor, CircleShape)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = textPrimary, fontWeight = FontWeight.Bold)) {
                            append("9 ")
                        }
                        withStyle(style = SpanStyle(color = textSecondary)) {
                            append("Subjects Tracked")
                        }
                    },
                    fontSize = 13.sp
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

        Spacer(Modifier.height(12.dp))

        // 🔹 ATTENDANCE LIST
        val attendanceData = listOf(
            AttendanceItem("Advanced Calculus and Complex Analysis", "21MAB102T", 76, "WARNING", "THEORY", 1),
            AttendanceItem("Semiconductor Physics and Computational Methods", "21PYB102J", 84, "SAFE", "THEORY", 5),
            AttendanceItem("Object Oriented Design and Programming", "21CSC101T", 77, "WARNING", "THEORY", 1),
            AttendanceItem("Engineering Graphics and Design", "21MES102L", 80, "WARNING", "PRACTICAL", 2),
            AttendanceItem("Constitution of India", "21LEH101T", 92, "SAFE", "PRACTICAL", 4)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(attendanceData) { item ->
                AttendanceCard(item, isDark)
            }
        }
    }
}

data class AttendanceItem(
    val name: String,
    val code: String,
    val percentage: Int,
    val status: String,
    val type: String,
    val skip: Int
)

@Composable
fun AttendanceCard(item: AttendanceItem, isDark: Boolean) {
    val statusColor = when (item.status) {
        "SAFE" -> Color(0xFF00E676)
        "WARNING" -> Color(0xFFFFA000)
        "CRITICAL" -> Color(0xFFFF5252)
        else -> Color.Gray
    }

    val cardColor = if (isDark) Color(0xFF111821) else Color.White
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .drawBehind {
                drawLine(
                    color = statusColor,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 10f
                )
            },
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Type Chip
                Surface(
                    color = Color(0xFF00BFA6).copy(alpha = 0.1f),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(0.5.dp, Color(0xFF00BFA6).copy(alpha = 0.3f))
                ) {
                    Text(
                        text = item.type,
                        color = Color(0xFF00BFA6),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    text = item.name,
                    color = textPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp
                )

                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(item.code, color = textSecondary, fontSize = 12.sp)
                    Text("  •  ", color = textSecondary)
                    Text(
                        "Skip ${item.skip}",
                        color = Color(0xFF00CFE8),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Vertical Divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(50.dp)
                    .background(Color.Gray.copy(alpha = 0.1f))
            )

            Spacer(Modifier.width(16.dp))

            // Percentage Circle
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Canvas(modifier = Modifier.size(54.dp)) {
                        drawArc(
                            color = statusColor.copy(alpha = 0.1f),
                            startAngle = 0f,
                            sweepAngle = 360f,
                            useCenter = false,
                            style = Stroke(width = 6f)
                        )
                        drawArc(
                            color = statusColor,
                            startAngle = -90f,
                            sweepAngle = (item.percentage / 100f) * 360f,
                            useCenter = false,
                            style = Stroke(width = 6f, cap = StrokeCap.Round)
                        )
                    }
                    Text(
                        "${item.percentage}%",
                        color = statusColor,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    item.status,
                    color = statusColor,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
