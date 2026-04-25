package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalendarScreen(isDark: Boolean) {

    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val accentColor = Color(0xFF00CFE8)
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)
    val cardBg = if (isDark) Color(0xFF111821) else Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(8.dp))
        StandardHeader("Calendar", "Day orders & events", isDark)
        Spacer(Modifier.height(12.dp))

        // 🔹 SUMMARY ROW
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = accentColor, fontWeight = FontWeight.Bold)) {
                        append("3 ")
                    }
                    withStyle(style = SpanStyle(color = textSecondary)) {
                        append("events in April 2026")
                    }
                },
                fontSize = 14.sp
            )

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(32.dp)
                    .background(chipContainerBg, CircleShape)
            ) {
                Icon(Icons.Outlined.Refresh, null, tint = accentColor, modifier = Modifier.size(18.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 TODAY / TOMORROW ROW
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // TODAY
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, accentColor.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                    .background(accentColor.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                    .padding(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text("TODAY", color = accentColor, fontSize = 10.sp, fontWeight = FontWeight.Black)
                        Text("22", color = textPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                    Surface(
                        color = accentColor.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Day 2", color = accentColor, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                    }
                }
            }

            // TOMORROW
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                    .background(chipContainerBg.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                    .padding(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text("TOMORROW", color = accentColor, fontSize = 10.sp, fontWeight = FontWeight.Black)
                        Text("23", color = textPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                    Text("Holiday", color = Color(0xFFFF5252), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 CALENDAR CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = cardBg),
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.1f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Month Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {}) { Icon(Icons.Default.ChevronLeft, null, tint = textSecondary) }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("April 2026", color = textPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("3 holidays • 0 events", color = textSecondary, fontSize = 12.sp)
                    }
                    IconButton(onClick = {}) { Icon(Icons.Default.ChevronRight, null, tint = textSecondary) }
                }

                Spacer(Modifier.height(16.dp))

                // Chips
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Surface(
                        color = accentColor,
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier.clickable { }
                    ) {
                        Text("All", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp))
                    }
                    Surface(
                        color = chipContainerBg,
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier.clickable { }
                    ) {
                        Text("Holidays", color = textSecondary, fontSize = 12.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp))
                    }
                }

                Spacer(Modifier.height(20.dp))

                // Days Labels
                Row(modifier = Modifier.fillMaxWidth()) {
                    listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT").forEach { day ->
                        Text(
                            text = day,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            color = textSecondary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                // Calendar Grid (Manual mock for April 2026)
                val days = (1..30).toList()
                val startOffset = 3 // Wed
                val rows = (days.size + startOffset + 6) / 7

                for (r in 0 until rows) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        for (c in 0 until 7) {
                            val dayIndex = r * 7 + c - startOffset
                            Box(modifier = Modifier.weight(1f).aspectRatio(1f), contentAlignment = Alignment.Center) {
                                if (dayIndex in 0 until days.size) {
                                    val day = days[dayIndex]
                                    val isToday = day == 22
                                    val isHoliday = day == 3 || day == 14 || day == 23
                                    val isPast = day < 22
                                    val isWeekend = c == 0 || c == 6

                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(2.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(
                                                when {
                                                    isToday -> accentColor
                                                    isHoliday -> Color(0xFFFF5252).copy(alpha = 0.15f)
                                                    isPast -> chipContainerBg.copy(alpha = 0.3f)
                                                    else -> Color.Transparent
                                                }
                                            )
                                            .padding(vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = day.toString(),
                                            color = when {
                                                isToday -> Color.Black
                                                isHoliday -> Color(0xFFFF5252)
                                                else -> textPrimary
                                            },
                                            fontSize = 14.sp,
                                            fontWeight = if (isToday) FontWeight.Bold else FontWeight.Medium,
                                            lineHeight = 14.sp
                                        )
                                        
                                        if (!isWeekend) {
                                            Text(
                                                text = "D${(day % 5) + 1}",
                                                color = if (isToday) Color.Black.copy(alpha = 0.7f) else textSecondary,
                                                fontSize = 10.sp,
                                                lineHeight = 10.sp
                                            )
                                        }

                                        if (isHoliday) {
                                            Spacer(Modifier.height(2.dp))
                                            Box(Modifier.size(4.dp).background(Color(0xFFFF5252), CircleShape))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))

                // Legend
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    LegendItem("Today", accentColor)
                    LegendItem("Holiday", Color(0xFFFF5252))
                    LegendItem("Event", Color(0xFF00BFA6))
                    LegendItem("Class day", Color(0xFF00E5FF))
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // 🔹 EVENTS LIST SECTION
        Text(
            "April 2026 — Events",
            color = textPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        Spacer(Modifier.height(16.dp))

        val events = listOf(
            CalendarEvent("3", "Good Friday - Holiday", "Fri · No class", Color(0xFFFF5252)),
            CalendarEvent("14", "Tamil New Year's Day / Dr. B.R. Ambedkar's Birthday - Holiday", "Tue · No class", Color(0xFFFF5252)),
            CalendarEvent("23", "General Election - Holiday", "Thu · No class", Color(0xFFFF5252))
        )

        events.forEach { event ->
            EventCard(event, isDark)
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.height(100.dp))
    }
}

data class CalendarEvent(val day: String, val title: String, val subtitle: String, val color: Color)

@Composable
fun LegendItem(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(8.dp).background(color, CircleShape))
        Spacer(Modifier.width(6.dp))
        Text(label, color = Color(0xFF9AA4AE), fontSize = 11.sp)
    }
}

@Composable
fun EventCard(event: CalendarEvent, isDark: Boolean) {
    val cardBg = if (isDark) Color(0xFF162638).copy(alpha = 0.6f) else Color.White
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = event.color,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 8f
                )
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.05f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(event.color.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(event.day, color = event.color, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.width(16.dp))

            Column {
                Text(event.title, color = textPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold, lineHeight = 18.sp)
                Spacer(Modifier.height(4.dp))
                Text(event.subtitle, color = textSecondary, fontSize = 12.sp)
            }
        }
    }
}
