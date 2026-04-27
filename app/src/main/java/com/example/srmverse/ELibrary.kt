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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ELibraryScreen(isDark: Boolean, onMenuClick: () -> Unit, onNavigate: (String) -> Unit) {
    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val accentColor = Color(0xFF00CFE8)
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)
    
    var selectedTab by remember { mutableStateOf("Documents") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        StandardHeader("E-Library", "Unlock exclusive study materials", isDark, onMenuClick, onNavigate)
        Spacer(Modifier.height(20.dp))

        // 🔹 SUMMARY ROW
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFF00BFA6), fontWeight = FontWeight.Bold)) {
                        append("2168 ")
                    }
                    withStyle(style = SpanStyle(color = textSecondary)) {
                        append("documents available")
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

        // 🔹 TABS
        SegmentedChips(
            options = listOf("Documents", "Purchase", "Transactions"),
            selected = selectedTab,
            onSelect = { selectedTab = it },
            isDark = isDark
        )

        Spacer(Modifier.height(16.dp))

        // 🔹 SEARCH BAR
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text("Search all documents...", color = textSecondary, fontSize = 14.sp)
            },
            leadingIcon = {
                Icon(Icons.Default.Search, null, tint = textSecondary, modifier = Modifier.size(20.dp))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = chipContainerBg,
                focusedContainerColor = chipContainerBg,
                unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.1f),
                focusedIndicatorColor = accentColor.copy(alpha = 0.5f),
                cursorColor = accentColor,
                focusedTextColor = textPrimary,
                unfocusedTextColor = textPrimary
            )
        )

        Spacer(Modifier.height(20.dp))

        // 🔹 DOCUMENTS LIST
        val docs = listOf(
            DocItem("21PYB10LJ", "Physics_ Electromagnetic Theory, Quan...", "Apr 16, 2026", "Locked", 3),
            DocItem("21PYB104J", "Physics_ Mechanics", "Apr 16, 2026", "Locked", 3),
            DocItem("21PYB101JR", "Physics_ Electromagnetic Theory, Quan...", "Apr 16, 2026", "Locked", 3),
            DocItem("21PYB101JR", "Engineering Physics", "Apr 16, 2026", "Locked", 3)
        )

        docs.forEach { doc ->
            DocCard(doc, isDark)
            Spacer(Modifier.height(16.dp))
        }

        Spacer(Modifier.height(100.dp))
    }
}

data class DocItem(val code: String, val title: String, val date: String, val status: String, val price: Int)

@Composable
fun DocCard(doc: DocItem, isDark: Boolean) {
    val cardBg = if (isDark) Color(0xFF111821) else Color.White
    val boxBg = if (isDark) Color(0xFF162638) else Color(0xFFF0F4F8)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val accentColor = Color(0xFF00BFA6)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(boxBg, RoundedCornerShape(12.dp))
                    .border(1.dp, Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Surface(
                            color = accentColor.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(0.5.dp, accentColor.copy(alpha = 0.3f))
                        ) {
                            Text(
                                doc.code,
                                color = accentColor,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            doc.title,
                            color = textPrimary,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                        Spacer(Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CalendarToday, null, tint = textSecondary, modifier = Modifier.size(12.dp))
                            Spacer(Modifier.width(4.dp))
                            Text(doc.date, color = textSecondary, fontSize = 12.sp)
                            Text("  •  ", color = textSecondary)
                            Text(doc.status, color = Color(0xFFFFA000), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Text("  •  ", color = textSecondary)
                            Text("₹${doc.price}", color = Color(0xFFFFA000), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(Modifier.width(12.dp))
                    
                    // Locked Circle
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .border(1.dp, Color(0xFFFFA000).copy(alpha = 0.3f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Lock, null, tint = Color(0xFFFFA000), modifier = Modifier.size(18.dp))
                        }
                        Spacer(Modifier.height(4.dp))
                        Text("LOCKED", color = Color(0xFFFFA000), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // Bottom Buttons
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f).height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF162638)),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, accentColor.copy(alpha = 0.2f))
                ) {
                    Icon(Icons.Outlined.Share, null, tint = accentColor, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("WhatsApp", color = accentColor, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f).height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF222B35)),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.2f))
                ) {
                    Icon(Icons.Default.Link, null, tint = Color.LightGray, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Copy Link", color = Color.LightGray, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
