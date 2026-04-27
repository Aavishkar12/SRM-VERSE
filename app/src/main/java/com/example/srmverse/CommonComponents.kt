package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.ViewQuilt
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ---------------- HEADER ----------------

@Composable
fun StandardHeader(title: String, subtitle: String, isDark: Boolean, onMenuClick: () -> Unit, onNavigate: (String) -> Unit) {
    val textPrimary = if (isDark) Color.White else Color(0xFF1A1C1E)
    val textSecondary = if (isDark) Color(0xFF9AA4AE) else Color(0xFF74777F)
    val headerCardBg = if (isDark) Color(0xFF162638) else Color.White

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = headerCardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isDark) 4.dp else 1.dp),
        border = if (!isDark) BorderStroke(1.dp, Color(0xFFE1E2EC)) else null
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
                        .border(1.dp, Color(0xFF00BFA6).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                        .padding(8.dp)
                        .clickable { onMenuClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.AutoMirrored.Filled.ViewQuilt, null, tint = Color(0xFF00BFA6))
                }

                Spacer(Modifier.width(12.dp))

                Column {
                    Text(title, color = textPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(subtitle, color = textSecondary, fontSize = 12.sp)
                }
            }

            Row {
                IconButton(onClick = { onNavigate("feed") }) {
                    Icon(Icons.Default.AutoAwesome, null, tint = textSecondary)
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Notifications, null, tint = textSecondary)
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Settings, null, tint = textSecondary)
                }
            }
        }
    }
}

// ---------------- SEGMENTED ----------------

@Composable
fun SegmentedChips(options: List<String>, selected: String, onSelect: (String) -> Unit, isDark: Boolean) {
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFF1F5F9)
    val accentColor = if (isDark) Color(0xFF00CFE8) else Color(0xFF00BFA6)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(chipContainerBg, RoundedCornerShape(30.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        options.forEach { option ->
            val isSelected = selected == option

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(30.dp))
                    .background(if (isSelected) Color.White else Color.Transparent)
                    .clickable { onSelect(option) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    option,
                    color = if (isSelected) accentColor else Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// ---------------- PROGRESS ----------------

@Composable
fun CircularProgressBox(percentage: Int, bottomText: String, statusColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.size(50.dp)) {
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
                    sweepAngle = (percentage / 100f) * 360f,
                    useCenter = false,
                    style = Stroke(width = 6f, cap = StrokeCap.Round)
                )
            }

            Text("$percentage%", color = statusColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }

        if (bottomText.isNotEmpty()) {
            Spacer(Modifier.height(4.dp))
            Text(bottomText, color = statusColor, fontSize = 9.sp)
        }
    }
}

// ---------------- DRAWER (🔥 UPDATED) ----------------

@Composable
fun AppDrawer(
    isDark: Boolean,
    onNavigate: (String) -> Unit,
    onClose: () -> Unit,
    onSignOut: () -> Unit
) {
    val bgColor = if (isDark) Color(0xFF0B1218) else Color(0xFFFDFBFF)
    val textPrimary = if (isDark) Color.White else Color(0xFF1A1C1E)
    val textSecondary = if (isDark) Color(0xFF8A97A6) else Color(0xFF6B7280)
    val accentColor = Color(0xFF00BFA6)

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.78f)
            .background(bgColor)
            .statusBarsPadding()
            .padding(18.dp)
    ) {

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(34.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text("SRM VERSE", color = accentColor, fontWeight = FontWeight.Bold)
            }

            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, null, tint = textSecondary)
            }
        }

        Spacer(Modifier.height(20.dp))

        // Profile
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable { onNavigate("profile") }
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, null, tint = textSecondary)
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text("Aavishkar Singh", color = textPrimary, fontWeight = FontWeight.SemiBold)
                Text("Student", color = textSecondary, fontSize = 11.sp)
            }
        }

        Spacer(Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            item { DrawerSectionHeader("ACADEMICS", textSecondary) }

            items(
                listOf(
                    Triple("Attendance", Icons.Default.CheckCircle, "attendance_main"),
                    Triple("Marks", Icons.Default.BarChart, "marks"),
                    Triple("Timetable", Icons.Default.Schedule, "timetable"),
                    Triple("Calendar", Icons.Default.CalendarMonth, "calendar")
                )
            ) {
                DrawerItemModern(it.first, it.second, isDark, { onNavigate(it.third) })
            }

            item {
                Spacer(Modifier.height(16.dp))
                DrawerSectionHeader("DISCOVER", textSecondary)
            }

            item { DrawerItemModern("Dashboard", Icons.Default.Dashboard, isDark, { onNavigate("dashboard") }) }

            items(
                listOf(
                    Triple("E-Library", Icons.AutoMirrored.Filled.MenuBook, "elibrary"),
                    Triple("Internships", Icons.Default.Work, "internships"),
                    Triple("Hackathons", Icons.Default.EmojiEvents, "hackathons"),
                    Triple("Team Finder", Icons.Default.Groups, "teamfinder"),
                    Triple("Conferences", Icons.AutoMirrored.Filled.MenuBook, "conferences"),
                    Triple("Tech News", Icons.Default.Newspaper, "technews")
                )
            ) {
                DrawerItemModern(it.first, it.second, isDark, {
                    if (it.third.isNotEmpty()) onNavigate(it.third)
                })
            }

            item {
                Spacer(Modifier.height(16.dp))
                DrawerSectionHeader("CAMPUS", textSecondary)
            }

            item { DrawerItemModern("Marketplace", Icons.Default.LocalOffer, isDark, { onNavigate("marketplace") }) }

            item {
                Spacer(Modifier.height(20.dp))
                DrawerItemModern("Sign Out", Icons.AutoMirrored.Filled.Logout, isDark, onSignOut, true)
            }
        }
    }
}

// ---------------- MODERN ITEM ----------------

@Composable
fun DrawerItemModern(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isDark: Boolean,
    onClick: () -> Unit,
    isDanger: Boolean = false
) {
    val textColor = if (isDanger) Color.Red else if (isDark) Color.White else Color.Black
    val iconColor = if (isDanger) Color.Red else if (isDark) Color(0xFF00CFE8) else Color(0xFF00BFA6)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
            .padding(vertical = 12.dp, horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = iconColor, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(16.dp))
        Text(label, color = textColor, fontSize = 14.sp)
    }
}

// ---------------- SECTION HEADER ----------------

@Composable
fun DrawerSectionHeader(title: String, color: Color) {
    Text(
        title,
        color = color,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 6.dp)
    )
}