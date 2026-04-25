package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
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
fun TimetableScreen(isDark: Boolean) {
    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val accentColor = Color(0xFF00CFE8)
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)
    var selectedDay by remember { mutableStateOf("D3") }

    Column(modifier = Modifier.fillMaxSize().background(bgColor).padding(horizontal = 16.dp).verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(56.dp))
        StandardHeader("Timetable", "Your weekly schedule", isDark)
        Spacer(Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(buildAnnotatedString { withStyle(SpanStyle(Color(0xFF00BFA6), fontWeight = FontWeight.Bold)) { append("4 ") }; withStyle(SpanStyle(textSecondary)) { append("classes on Day 3") } }, fontSize = 14.sp)
            IconButton(onClick = {}, modifier = Modifier.size(32.dp).background(chipContainerBg, CircleShape)) { Icon(Icons.Outlined.Refresh, null, tint = accentColor, modifier = Modifier.size(18.dp)) }
        }

        Spacer(Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            OutlinedButton(onClick = {}, border = BorderStroke(1.dp, Color(0xFF00BFA6).copy(0.6f)), shape = RoundedCornerShape(30.dp), modifier = Modifier.height(36.dp), colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF00BFA6))) {
                Icon(Icons.Outlined.FileDownload, null, modifier = Modifier.size(18.dp)); Spacer(Modifier.width(6.dp)); Text("Download PDF", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
            Surface(color = if (isDark) Color(0xFF1A222B) else Color(0xFFE0E0E0), shape = RoundedCornerShape(30.dp), modifier = Modifier.height(36.dp)) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 16.dp)) { Text("TODAY: D2", color = Color(0xFF00BFA6), fontSize = 11.sp, fontWeight = FontWeight.Bold) }
            }
        }

        Spacer(Modifier.height(16.dp))
        SegmentedChips(listOf("D1", "D2", "D3", "D4", "D5"), selectedDay, { selectedDay = it }, isDark)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = "", onValueChange = {},
            placeholder = { Text("Search by subject, faculty or room", color = textSecondary, fontSize = 14.sp) },
            leadingIcon = { Icon(Icons.Default.Search, null, tint = textSecondary, modifier = Modifier.size(20.dp)) },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(unfocusedContainerColor = chipContainerBg, focusedContainerColor = chipContainerBg, unfocusedIndicatorColor = Color.Gray.copy(0.1f), focusedIndicatorColor = Color(0xFF00BFA6).copy(0.5f), cursorColor = Color(0xFF00BFA6), focusedTextColor = textPrimary, unfocusedTextColor = textPrimary)
        )

        val timetableItems = listOf(
            TimetableItem("Electrical and Electronics Engineering", "Dr.Y.Jeyashree", "Room 822", "08:00", "09:40", 78, "40/51"),
            TimetableItem("Semiconductor Physics and Computational Methods", "Dr. Tusharbhai Himmatbhai Rana", "Room 822", "10:40", "11:30", 84, "38/45")
        )

        timetableItems.forEach { item ->
            TimetableCard(item, isDark)
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.height(100.dp))
    }
}

data class TimetableItem(val name: String, val faculty: String, val room: String, val startTime: String, val endTime: String, val percentage: Int, val fraction: String)

@Composable
fun TimetableCard(item: TimetableItem, isDark: Boolean) {
    val accentColor = Color(0xFF00BFA6)
    val boxBg = if (isDark) Color(0xFF162638) else Color(0xFFF0F4F8)
    
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        colors = CardDefaults.cardColors(if (isDark) Color(0xFF111821) else Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(boxBg, RoundedCornerShape(16.dp))
                .border(1.dp, Color.Gray.copy(0.1f), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(60.dp)
                ) {
                    Icon(
                        Icons.Outlined.Schedule,
                        null,
                        tint = accentColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        item.startTime,
                        color = if (isDark) Color.White else Color.Black,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        "to",
                        color = Color(0xFF9AA4AE),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        item.endTime,
                        color = if (isDark) Color.White else Color.Black,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                
                Spacer(Modifier.width(12.dp))
                Box(Modifier.width(1.dp).height(70.dp).background(Color.Gray.copy(0.25f)))
                Spacer(Modifier.width(12.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        item.name,
                        color = if (isDark) Color.White else Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 18.sp,
                        maxLines = 2
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Person,
                            null,
                            tint = Color(0xFF9AA4AE),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            item.faculty,
                            color = Color(0xFF9AA4AE),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.LocationOn,
                            null,
                            tint = Color(0xFF9AA4AE),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            item.room,
                            color = Color(0xFF9AA4AE),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                Spacer(Modifier.width(10.dp))
                CircularProgressBox(
                    item.percentage,
                    item.fraction,
                    if (item.percentage >= 84) Color(0xFF00E676) 
                    else if (item.percentage >= 75) Color(0xFFFFA000) 
                    else Color(0xFFFF5252)
                )
            }
        }
    }
}
