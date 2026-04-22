package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

@Composable
fun AttendanceScreen(isDark: Boolean) {
    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val accentColor = Color(0xFF00CFE8)
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)

    var selectedType by remember { mutableStateOf("All") }
    var selectedStatus by remember { mutableStateOf("All") }

    Column(modifier = Modifier.fillMaxSize().background(bgColor).padding(horizontal = 16.dp)) {
        Spacer(Modifier.height(16.dp))
        StandardHeader("Attendance", "Track your classes", isDark)
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = "", onValueChange = {},
            placeholder = { Text("Search by code, subject or faculty", color = textSecondary, fontSize = 14.sp) },
            leadingIcon = { Icon(Icons.Default.Search, null, tint = textSecondary, modifier = Modifier.size(20.dp)) },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(unfocusedContainerColor = chipContainerBg, focusedContainerColor = chipContainerBg, unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.1f), focusedIndicatorColor = accentColor.copy(alpha = 0.5f), cursorColor = accentColor, focusedTextColor = textPrimary, unfocusedTextColor = textPrimary)
        )

        Spacer(Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SegmentedChips(listOf("All", "Theory", "Practical", "Predict"), selectedType, { selectedType = it }, isDark)
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                listOf("All", "Safe", "Low", "Critical").forEach { status ->
                    val isSelected = selectedStatus == status
                    Box(modifier = Modifier.weight(1f).height(38.dp).clip(RoundedCornerShape(30.dp)).background(if (isSelected) accentColor else chipContainerBg).border(1.dp, if (isSelected) accentColor else Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(30.dp)).clickable { selectedStatus = status }, contentAlignment = Alignment.Center) {
                        Text(status, color = if (isSelected) Color.Black else textSecondary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(8.dp).background(accentColor, CircleShape))
                Spacer(Modifier.width(8.dp))
                Text(buildAnnotatedString { withStyle(SpanStyle(textPrimary, fontWeight = FontWeight.Bold)) { append("9 ") }; withStyle(SpanStyle(textSecondary)) { append("Subjects Tracked") } }, fontSize = 13.sp)
            }
            IconButton(onClick = {}, modifier = Modifier.size(32.dp).background(chipContainerBg, CircleShape)) { Icon(Icons.Outlined.Refresh, null, tint = accentColor, modifier = Modifier.size(18.dp)) }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(top = 12.dp, bottom = 100.dp)) {
            items(listOf(Triple("Advanced Calculus and Complex Analysis", 76, "WARNING"), Triple("Semiconductor Physics and Computational Methods", 84, "SAFE"), Triple("Object Oriented Design and Programming", 77, "WARNING"), Triple("Engineering Graphics and Design", 80, "WARNING"), Triple("Environmental Science", 73, "CRITICAL"))) { data ->
                AttendanceCard(data, isDark)
            }
        }
    }
}

@Composable
fun AttendanceCard(data: Triple<String, Int, String>, isDark: Boolean) {
    val statusColor = when (data.third) { "SAFE" -> Color(0xFF00E676); "WARNING" -> Color(0xFFFFA000); else -> Color(0xFFFF5252) }
    val boxBg = if (isDark) Color(0xFF162638) else Color(0xFFF0F4F8)

    Card(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(if (isDark) Color(0xFF111821) else Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(boxBg, RoundedCornerShape(12.dp))
                    .border(1.dp, Color.Gray.copy(0.1f), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Surface(
                            color = Color(0xFF00BFA6).copy(0.1f),
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(0.5.dp, Color(0xFF00BFA6).copy(0.3f))
                        ) {
                            Text(
                                "THEORY",
                                color = Color(0xFF00BFA6),
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            data.first,
                            color = if (isDark) Color.White else Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("21MAB102T", color = Color(0xFF9AA4AE), fontSize = 12.sp)
                            Text("  •  ", color = Color(0xFF9AA4AE))
                            Text(
                                "Skip 2",
                                color = Color(0xFF00CFE8),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Box(Modifier.width(1.dp).height(50.dp).background(Color.Gray.copy(0.1f)))
                    Spacer(Modifier.width(16.dp))
                    CircularProgressBox(data.second, data.third, statusColor)
                }
            }
        }
    }
}
