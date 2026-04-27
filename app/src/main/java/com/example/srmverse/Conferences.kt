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
fun ConferencesScreen(isDark: Boolean, onMenuClick: () -> Unit, onNavigate: (String) -> Unit) {
    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)
    val tealColor = Color(0xFF00BFA6)

    var selectedTab by remember { mutableStateOf("Global Research") }

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
                            .border(1.dp, tealColor.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                            .clickable { onMenuClick() }
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.ViewColumn, null, tint = tealColor, modifier = Modifier.size(24.dp))
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("Conferences", color = textPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Browse academic papers", color = textSecondary, fontSize = 12.sp)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { onNavigate("feed") }) { Icon(Icons.Default.AutoAwesome, null, tint = textSecondary, modifier = Modifier.size(20.dp)) }
                    IconButton(onClick = {}) { Icon(Icons.Default.Notifications, null, tint = textSecondary, modifier = Modifier.size(22.dp)) }
                    IconButton(onClick = {}) { Icon(Icons.Default.Settings, null, tint = textSecondary, modifier = Modifier.size(22.dp)) }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 SUMMARY ROW
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = tealColor, fontWeight = FontWeight.Bold)) {
                        append("20 ")
                    }
                    withStyle(style = SpanStyle(color = textSecondary)) {
                        append("conferences in india")
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
                Icon(Icons.Outlined.Refresh, null, tint = tealColor, modifier = Modifier.size(18.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 PLATFORM TABS
        Surface(
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = RoundedCornerShape(24.dp),
            color = chipContainerBg
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("Global Research", "Easy Chair CFP").forEach { option ->
                    val isSelected = option == selectedTab
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) (if(isDark) Color(0xFF2C353F) else Color.White) else Color.Transparent)
                            .clickable { selectedTab = option },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = option,
                            color = if (isSelected) tealColor else textSecondary,
                            fontSize = 14.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // 🔹 FILTERS
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // India Filter
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
                        Text("India", color = textPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }
                    Icon(Icons.Default.KeyboardArrowDown, null, tint = textSecondary, modifier = Modifier.size(18.dp))
                }
            }

            // Topics Filter
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
                        Text("All Topics", color = textPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }
                    Icon(Icons.Default.KeyboardArrowDown, null, tint = textSecondary, modifier = Modifier.size(18.dp))
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 CONFERENCE LIST
        val conferences = listOf(
            ConferenceData("International Symposium on Innovations in Dental Practice", "health and medicine"),
            ConferenceData("International Conference on Globalization, Urban Poverty, and Public Finance", "Interdisciplinary"),
            ConferenceData("International Conference on Family Medicine and Women's Health", "health and medicine"),
            ConferenceData("International Conference on Blockchain-Enhanced Cybersecurity in Big Data Analytics", "Engineering"),
            ConferenceData("International Conference on Philosophy of Mind and Human Behavior", "Social Science and Humanities"),
            ConferenceData("National Conference on Electronics and Electrical Engineering", "Engineering")
        )

        conferences.forEach { conf ->
            ConferenceCard(conf, isDark)
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.height(100.dp))
    }
}

data class ConferenceData(val title: String, val category: String)

@Composable
fun ConferenceCard(conf: ConferenceData, isDark: Boolean) {
    val cardBg = if (isDark) Color(0xFF111821) else Color.White
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val tagColor = Color(0xFF00BFA6)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = tagColor.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.LocalOffer, null, tint = tagColor, modifier = Modifier.size(12.dp))
                        Spacer(Modifier.width(4.dp))
                        Text(conf.category, color = tagColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Icon(Icons.Default.KeyboardArrowDown, null, tint = textSecondary, modifier = Modifier.size(20.dp))
            }
            
            Spacer(Modifier.height(12.dp))
            
            Text(
                text = conf.title,
                color = textPrimary,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            )
        }
    }
}
