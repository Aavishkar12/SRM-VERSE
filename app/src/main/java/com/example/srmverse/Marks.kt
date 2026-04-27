package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

@Composable
fun MarksScreen(isDark: Boolean, onMenuClick: () -> Unit, onNavigate: (String) -> Unit) {
    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF8FAFD)
    val textPrimary = if (isDark) Color.White else Color(0xFF1A1C1E)
    val textSecondary = if (isDark) Color.White else Color(0xFF74777F)
    val accentColor = if (isDark) Color(0xFF00CFE8) else Color(0xFF00BFA6)
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFF1F5F9)

    var selectedType by remember { mutableStateOf("All") }
    var selectedStatus by remember { mutableStateOf("All") }

    Column(modifier = Modifier.fillMaxSize().background(bgColor).padding(horizontal = 16.dp).verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(12.dp))
        StandardHeader("Marks", "Your internal scores", isDark, onMenuClick, onNavigate)
        Spacer(Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(buildAnnotatedString { withStyle(SpanStyle(accentColor, fontWeight = FontWeight.Bold)) { append("9 ") }; withStyle(SpanStyle(textSecondary)) { append("courses") } }, fontSize = 14.sp)
            IconButton(onClick = {}, modifier = Modifier.size(32.dp).background(chipContainerBg, CircleShape)) { Icon(Icons.Outlined.Refresh, null, tint = accentColor, modifier = Modifier.size(18.dp)) }
        }

        Spacer(Modifier.height(16.dp))
        SegmentedChips(listOf("All", "Theory", "Practical"), selectedType, { selectedType = it }, isDark)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = "", onValueChange = {},
            placeholder = { Text("Search by code or subject", color = textSecondary, fontSize = 14.sp) },
            leadingIcon = { Icon(Icons.Default.Search, null, tint = textSecondary, modifier = Modifier.size(20.dp)) },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = chipContainerBg, 
                focusedContainerColor = chipContainerBg, 
                unfocusedIndicatorColor = Color.Transparent, 
                focusedIndicatorColor = accentColor.copy(alpha = 0.5f), 
                cursorColor = accentColor, 
                focusedTextColor = textPrimary, 
                unfocusedTextColor = textPrimary
            )
        )

        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            listOf("All", "Safe", "Low", "Critical").forEach { status ->
                val isSelected = selectedStatus == status
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(if (isSelected) accentColor else chipContainerBg)
                        .border(1.dp, if (isSelected) accentColor else Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(30.dp))
                        .clickable { selectedStatus = status }, 
                    contentAlignment = Alignment.Center
                ) {
                    Text(status, color = if (isSelected) Color.Black else (if (isDark) Color.White else Color(0xFF44474E)), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        val marksItems = listOf(
            MarksItem("Communicative English", "THEORY", "3 tests", "33/35", 94, "O"),
            MarksItem("Semiconductor Physics and Computational Methods", "THEORY", "3 tests", "34.67/37", 94, "O"),
            MarksItem("Object Oriented Design and Programming", "THEORY", "3 tests", "27.88/35", 80, "A"),
            MarksItem("Engineering Graphics and Design", "PRACTICAL", "3 tests", "43/45", 96, "O"),
            MarksItem("Constitution of India", "PRACTICAL", "2 tests", "60/80", 75, "A"),
            MarksItem("Advanced Calculus and Complex Analysis", "THEORY", "3 tests", "31.4/35", 90, "A+"),
            MarksItem("Electrical and Electronics Engineering", "THEORY", "3 tests", "28.38/35", 81, "A+"),
            MarksItem("Semiconductor Physics and Computational Methods", "PRACTICAL", "0 tests", "0/0", 0, "-"),
            MarksItem("Environmental Science", "PRACTICAL", "2 tests", "42.5/50", 85, "A+")
        )

        marksItems.forEach { item ->
            MarksCard(item, isDark)
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.height(100.dp))
    }
}

data class MarksItem(val name: String, val type: String, val tests: String, val score: String, val percentage: Int, val grade: String)

@Composable
fun MarksCard(item: MarksItem, isDark: Boolean) {
    val statusColor = when { item.percentage >= 84 -> Color(0xFF00E676); item.percentage >= 75 -> Color(0xFFFFA000); item.percentage > 0 -> Color(0xFFFF5252); else -> Color.Gray }
    val boxBg = if (isDark) Color(0xFF162638) else Color(0xFFF1F4F9)
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(if (isDark) Color(0xFF111821) else Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isDark) 0.dp else 2.dp),
        border = if (!isDark) BorderStroke(1.dp, Color(0xFFE1E2EC).copy(alpha = 0.5f)) else null
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(boxBg, RoundedCornerShape(12.dp))
                    .border(1.dp, Color.Gray.copy(alpha = 0.05f), RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                color = Color(0xFF00BFA6).copy(0.1f),
                                shape = RoundedCornerShape(6.dp),
                                border = BorderStroke(0.5.dp, Color(0xFF00BFA6).copy(0.3f))
                            ) {
                                Text(
                                    item.type,
                                    color = Color(0xFF00BFA6),
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Black,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(Modifier.width(10.dp))
                            Text(
                                item.name,
                                color = if (isDark) Color.White else Color(0xFF1A1C1E),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 19.sp,
                                maxLines = 2
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Analytics, null, tint = if (isDark) Color(0xFF9AA4AE) else Color(0xFF74777F), modifier = Modifier.size(13.dp))
                            Spacer(Modifier.width(6.dp))
                            Text(item.tests, color = if (isDark) Color(0xFF9AA4AE) else Color(0xFF74777F), fontSize = 12.sp)
                            Text("  •  ", color = Color.Gray.copy(alpha = 0.5f))
                            Text(item.score, color = if (isDark) Color(0xFF00CFE8) else Color(0xFF00BFA6), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(Modifier.width(12.dp))
                    Box(Modifier.width(1.dp).height(45.dp).background(Color.Gray.copy(0.15f)))
                    Spacer(Modifier.width(12.dp))
                    CircularProgressBox(item.percentage, "Grade: ${item.grade}", statusColor)
                }
            }
        }
    }
}
