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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

@Composable
fun TechNewsScreen(isDark: Boolean, onMenuClick: () -> Unit, onNavigate: (String) -> Unit) {
    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)
    val tealColor = Color(0xFF00BFA6)

    var selectedSource by remember { mutableStateOf("TechCrunch") }

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
                        Text("Tech News", color = textPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Latest in tech", color = textSecondary, fontSize = 12.sp)
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
                        append("27 ")
                    }
                    withStyle(style = SpanStyle(color = textSecondary)) {
                        append("articles from $selectedSource")
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

        // 🔹 SOURCE TABS
        Surface(
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = RoundedCornerShape(24.dp),
            color = chipContainerBg
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("TechCrunch", "AIM").forEach { option ->
                    val isSelected = option == selectedSource
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) (if(isDark) Color(0xFF2C353F) else Color.White) else Color.Transparent)
                            .clickable { selectedSource = option },
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

        Spacer(Modifier.height(16.dp))

        // 🔹 NEWS LIST
        NewsCard(
            title = "Spotify's next frontier: fitness content",
            author = "Sarah Perez",
            time = "19 minutes ago",
            category = "APPS",
            isDark = isDark
        )
        
        Spacer(Modifier.height(16.dp))

        NewsCard(
            title = "Meta inks deal for solar power at night, beamed from space",
            author = "Tim Fernholz",
            time = "2 hours ago",
            category = "AI",
            isDark = isDark
        )

        Spacer(Modifier.height(100.dp))
    }
}

@Composable
fun NewsCard(
    title: String,
    author: String,
    time: String,
    category: String,
    isDark: Boolean
) {
    val cardBg = if (isDark) Color(0xFF111821) else Color.White
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val tealColor = Color(0xFF00BFA6)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.1f))
    ) {
        Column {
            // Image Placeholder (Using a Box with background)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Gray.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Image,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = textSecondary
                )
                
                // Overlay logo (Spotify icon etc)
                Icon(
                    Icons.Default.Radio, // Proxy for Spotify
                    null,
                    tint = Color.White,
                    modifier = Modifier.padding(12.dp).size(24.dp).background(Color.Black.copy(0.5f), CircleShape).padding(4.dp)
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = tealColor.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.LocalOffer, null, tint = tealColor, modifier = Modifier.size(12.dp))
                            Spacer(Modifier.width(4.dp))
                            Text(category, color = tealColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    
                    Spacer(Modifier.width(12.dp))
                    
                    Icon(Icons.Default.Schedule, null, tint = textSecondary, modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(time, color = textSecondary, fontSize = 12.sp)
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = title,
                    color = textPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp
                )

                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, null, tint = textSecondary, modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(author, color = textSecondary, fontSize = 12.sp)
                }
            }
        }
    }
}
