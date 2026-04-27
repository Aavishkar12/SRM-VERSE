package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.*

@Composable
fun DashboardScreen(isDark: Boolean, onMenuClick: () -> Unit, onNavigate: (String) -> Unit) {
    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val cardBg = if (isDark) Color(0xFF162638) else Color.White
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val tealColor = Color(0xFF00BFA6)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(8.dp))

        // 🔹 HEADER
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = cardBg),
            elevation = CardDefaults.cardElevation(if (isDark) 4.dp else 2.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .border(1.dp, tealColor.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                            .clickable { onMenuClick() }
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.ViewColumn, null, tint = tealColor, modifier = Modifier.size(24.dp))
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("Dashboard", color = textPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Your academic hub", color = textSecondary, fontSize = 12.sp)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { onNavigate("feed") }) { Icon(Icons.Default.AutoAwesome, null, tint = textSecondary, modifier = Modifier.size(20.dp)) }
                    IconButton(onClick = {}) { Icon(Icons.Default.Notifications, null, tint = textSecondary, modifier = Modifier.size(22.dp)) }
                    IconButton(onClick = {}) { Icon(Icons.Default.Settings, null, tint = textSecondary, modifier = Modifier.size(22.dp)) }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // 🔹 GREETING
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Good Evening, Aavishkar!",
                color = textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(
                onClick = { },
                modifier = Modifier.size(32.dp).background(cardBg, CircleShape)
            ) {
                Icon(Icons.Outlined.Refresh, null, tint = textSecondary, modifier = Modifier.size(18.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 ACADEMIC PERFORMANCE
        Text("ACADEMIC PERFORMANCE", color = textSecondary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        PerformanceCard("ACADEMIC", "OVERALL ATTENDANCE", "82.3%", "274/338", "Safe", 82, "ON TRACK", isDark)
        Spacer(Modifier.height(12.dp))
        PerformanceCard("PERFORMANCE", "INTERNAL MARKS", "87.8%", "330/382", "AVG", 88, "INTERNAL", isDark)

        Spacer(Modifier.height(24.dp))

        // 🔹 UPCOMING TASKS
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("UPCOMING TASKS", color = textSecondary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text("0 Tasks", color = textSecondary, fontSize = 12.sp)
        }
        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = cardBg)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("No upcoming tasks", color = textPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text("Stay ahead by organizing your s...", color = textSecondary, fontSize = 12.sp)
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = tealColor),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Add, null, tint = Color.White)
                    Text(" Create Task", color = Color.White)
                }
            }
        }
        
        Spacer(Modifier.height(12.dp))
        Text(
            text = buildAnnotatedString {
                append("FOR MORE CUSTOMIZATION, GO TO ")
                withStyle(SpanStyle(color = Color.Red, textDecoration = TextDecoration.Underline)) {
                    append("CALENDAR")
                }
            },
            color = textSecondary,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally).clickable { onNavigate("calendar") }
        )

        Spacer(Modifier.height(24.dp))

        // 🔹 TODAY'S CLASSES
        Text("TODAY'S CLASSES (DO - 4 • 5 CL)", color = textSecondary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        val classes = listOf(
            ClassData("08:50", "09:40", "Environmental Science", "Online", "Dr. Ravindran E", 75, "9/12", Color(0xFFFFA000)),
            ClassData("09:45", "10:35", "Constitution of India", "online", "Dr.Caleb Theodar M", 93, "13/14", Color(0xFF00BFA6)),
            ClassData("12:30", "14:15", "Semiconductor Physics and Computational Methods", "822", "Dr. Tusharbhai Himmatbhai Rana", 84, "38/45", Color(0xFFFFA000)),
            ClassData("14:20", "15:10", "Advanced Calculus and Complex Analysis", "822", "Dr. D.K.Sheena Christy", 76, "42/55", Color(0xFFFFA000)),
            ClassData("16:00", "16:50", "Electrical and Electronics Engineering", "822", "Dr.Y.Jeyashree", 76, "41/54", Color(0xFFFFA000))
        )

        classes.forEach { cls ->
            ClassCard(cls, isDark)
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.height(100.dp))
    }
}

data class ClassData(val start: String, val end: String, val title: String, val room: String, val teacher: String, val attendance: Int, val ratio: String, val accent: Color)

@Composable
fun PerformanceCard(tag: String, label: String, value: String, ratio: String, status: String, percentage: Int, footer: String, isDark: Boolean) {
    val cardBg = if (isDark) Color(0xFF162638) else Color.White
    val tealColor = Color(0xFF00BFA6)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        border = BorderStroke(1.dp, Color.Gray.copy(0.1f))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Surface(color = tealColor.copy(0.1f), shape = RoundedCornerShape(4.dp)) {
                    Text(tag, color = tealColor, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                }
                Spacer(Modifier.height(8.dp))
                Text(label, color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(value, color = if (isDark) Color.White else Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.width(8.dp))
                    Text(ratio, color = Color.Gray, fontSize = 14.sp)
                    Spacer(Modifier.width(8.dp))
                    Surface(color = tealColor.copy(0.1f), shape = RoundedCornerShape(8.dp)) {
                        Row(modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.AutoMirrored.Filled.TrendingUp, null, tint = tealColor, modifier = Modifier.size(12.dp))
                            Text(status, color = tealColor, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(progress = percentage / 100f, color = tealColor, strokeWidth = 4.dp, modifier = Modifier.size(50.dp))
                    Text("$percentage%", color = tealColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
                Text(footer, color = Color.Gray, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ClassCard(cls: ClassData, isDark: Boolean) {
    val cardBg = if (isDark) Color(0xFF162638) else Color.White
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(cls.start, color = cls.accent, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("to", color = Color.Gray, fontSize = 10.sp)
                Text(cls.end, color = cls.accent, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(cls.title, color = if (isDark) Color.White else Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, null, tint = Color.Gray, modifier = Modifier.size(12.dp))
                    Text(cls.room, color = Color.Gray, fontSize = 12.sp)
                    Spacer(Modifier.width(8.dp))
                    Text("•", color = Color.Gray)
                    Spacer(Modifier.width(8.dp))
                    Text(cls.teacher, color = Color.Gray, fontSize = 12.sp, maxLines = 1)
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(progress = cls.attendance / 100f, color = cls.accent, strokeWidth = 4.dp, modifier = Modifier.size(40.dp))
                    Text("${cls.attendance}%", color = cls.accent, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Text(cls.ratio, color = Color.Gray, fontSize = 10.sp)
            }
        }
    }
}
