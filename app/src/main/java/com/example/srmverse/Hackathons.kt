package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

@Composable
fun HackathonsScreen(isDark: Boolean, onMenuClick: () -> Unit, onNavigate: (String) -> Unit) {
    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)

    var selectedPlatform by remember { mutableStateOf("Unstop") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(8.dp))
        
        // 🔹 EXACT HEADER FROM SCREENSHOT
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = if (isDark) Color(0xFF162638) else Color.White),
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
                            .border(1.dp, Color(0xFF00BFA6).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                            .clickable { onMenuClick() }
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.GridView, null, tint = Color(0xFF00BFA6), modifier = Modifier.size(24.dp))
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("Hackathons", color = textPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Compete & build cool stuff", color = textSecondary, fontSize = 12.sp)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { onNavigate("feed") }) { Icon(Icons.Default.AutoAwesome, null, tint = textSecondary, modifier = Modifier.size(20.dp)) }
                    IconButton(onClick = {}) { Icon(Icons.Default.Notifications, null, tint = textSecondary, modifier = Modifier.size(22.dp)) }
                    IconButton(onClick = {}) { Icon(Icons.Default.Settings, null, tint = textSecondary, modifier = Modifier.size(22.dp)) }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // 🔹 SUMMARY ROW
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFF00BFA6), fontWeight = FontWeight.Bold)) {
                        append("64 ")
                    }
                    withStyle(style = SpanStyle(color = textSecondary)) {
                        append("Unstop hackathons")
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
                Icon(Icons.Outlined.Refresh, null, tint = Color(0xFF00BFA6), modifier = Modifier.size(18.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 PLATFORM CHIPS (EXACTLY SAME AS SCREENSHOT)
        Surface(
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = RoundedCornerShape(24.dp),
            color = chipContainerBg
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("Unstop", "Devfolio", "Devpost").forEach { option ->
                    val isSelected = option == selectedPlatform
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) Color.White else Color.Transparent)
                            .clickable { selectedPlatform = option },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = option,
                            color = if (isSelected) Color(0xFF00BFA6) else Color(0xFF9AA4AE),
                            fontSize = 14.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 FILTERS (EXACTLY SAME AS SCREENSHOT)
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // All Modes
            Surface(
                modifier = Modifier.weight(1f).height(44.dp),
                shape = RoundedCornerShape(22.dp),
                color = chipContainerBg,
                border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.1f))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Language, null, tint = textSecondary, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("All Modes", color = textPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }
                    Icon(Icons.Default.KeyboardArrowDown, null, tint = textSecondary, modifier = Modifier.size(18.dp))
                }
            }

            // Team Size
            Surface(
                modifier = Modifier.weight(1f).height(44.dp),
                shape = RoundedCornerShape(22.dp),
                color = chipContainerBg,
                border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.1f))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.FilterList, null, tint = textSecondary, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Any Team Size", color = textPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }
                    Icon(Icons.Default.KeyboardArrowDown, null, tint = textSecondary, modifier = Modifier.size(18.dp))
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 HACKATHON LIST (DATA FROM SCREENSHOT)
        val hackathons = listOf(
            HackathonData("Codequest: The Ultimate Tech Hunt", "1 - 2 MEMBERS", "12 days left", Color(0xFF1A1A1A)),
            HackathonData("Bureau of Indian Standards x Sigma Squad AI Hackathon", "1 - 4 MEMBERS", "6 days left", Color(0xFF001F3F)),
            HackathonData("CODEFUSION 2k26", "2 - 4 MEMBERS", "3 months left", Color(0xFFF5F5F5)),
            HackathonData("Zenith 5.0", "2 - 4 MEMBERS", "1 days left", Color.Black),
            HackathonData("Summer Hackathon National Wide Competition 2026", "1 - 4 MEMBERS", "12 days left", Color(0xFFE3F2FD))
        )

        hackathons.forEach { item ->
            HackathonListCard(item, isDark)
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.height(100.dp))
    }
}

data class HackathonData(val title: String, val members: String, val timeLeft: String, val placeholderColor: Color)

@Composable
fun HackathonListCard(item: HackathonData, isDark: Boolean) {
    val cardBg = if (isDark) Color(0xFF111821) else Color.White
    val boxBg = if (isDark) Color(0xFF162638) else Color(0xFFF0F4F8)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val tagBg = Color(0xFF00BFA6).copy(alpha = 0.1f)
    val tagText = Color(0xFF00BFA6)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(2.dp),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.05f))
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(boxBg, RoundedCornerShape(12.dp))
                    .border(1.dp, Color.Gray.copy(0.1f), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Logo Placeholder
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(item.placeholderColor),
                        contentAlignment = Alignment.Center
                    ) {
                         if (item.placeholderColor == Color.White || item.placeholderColor == Color(0xFFF5F5F5)) {
                            Text(item.title.take(1), color = Color.Gray, fontWeight = FontWeight.Bold)
                         }
                    }
                    
                    Spacer(Modifier.width(16.dp))
                    
                    Column(modifier = Modifier.weight(1f)) {
                        Surface(
                            color = tagBg,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Groups, null, tint = tagText, modifier = Modifier.size(14.dp))
                                Spacer(Modifier.width(4.dp))
                                Text(item.members, color = tagText, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        
                        Spacer(Modifier.height(8.dp))
                        
                        Text(
                            item.title,
                            color = textPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 18.sp,
                            maxLines = 2
                        )
                        
                        Spacer(Modifier.height(8.dp))
                        
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Schedule, null, tint = Color(0xFF00CFE8), modifier = Modifier.size(14.dp))
                            Spacer(Modifier.width(4.dp))
                            Text(item.timeLeft, color = Color(0xFF00CFE8), fontSize = 12.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                    
                    Icon(Icons.Default.KeyboardArrowDown, null, tint = textSecondary, modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}
