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
fun MarksScreen(isDark: Boolean) {
    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val accentColor = Color(0xFF00CFE8)
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)

    var selectedType by remember { mutableStateOf("All") }
    var selectedStatus by remember { mutableStateOf("All") }

    Column(modifier = Modifier.fillMaxSize().background(bgColor).padding(horizontal = 16.dp)) {
        Spacer(Modifier.height(16.dp))
        StandardHeader("Marks", "Your internal scores", isDark)
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
            colors = TextFieldDefaults.colors(unfocusedContainerColor = chipContainerBg, focusedContainerColor = chipContainerBg, unfocusedIndicatorColor = Color.Gray.copy(0.1f), focusedIndicatorColor = accentColor.copy(0.5f), cursorColor = accentColor, focusedTextColor = textPrimary, unfocusedTextColor = textPrimary)
        )

        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            listOf("All", "Safe", "Low", "Critical").forEach { status ->
                val isSelected = selectedStatus == status
                Box(modifier = Modifier.weight(1f).height(38.dp).clip(RoundedCornerShape(30.dp)).background(if (isSelected) accentColor else chipContainerBg).border(1.dp, if (isSelected) accentColor else Color.Gray.copy(0.1f), RoundedCornerShape(30.dp)).clickable { selectedStatus = status }, contentAlignment = Alignment.Center) {
                    Text(status, color = if (isSelected) Color.Black else textSecondary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp)) {
            items(listOf(MarksItem("Communicative English", "THEORY", "3 tests", "33/35", 94, "O"), MarksItem("Semiconductor Physics and Computational Methods", "THEORY", "3 tests", "34.67/37", 94, "O"), MarksItem("Object Oriented Design and Programming", "THEORY", "3 tests", "27.88/35", 80, "A"), MarksItem("Engineering Graphics and Design", "PRACTICAL", "3 tests", "43/45", 96, "O"), MarksItem("Constitution of India", "PRACTICAL", "2 tests", "60/80", 75, "A"))) { item ->
                MarksCard(item, isDark)
            }
        }
    }
}

data class MarksItem(val name: String, val type: String, val tests: String, val score: String, val percentage: Int, val grade: String)

@Composable
fun MarksCard(item: MarksItem, isDark: Boolean) {
    val statusColor = when { item.percentage >= 84 -> Color(0xFF00E676); item.percentage >= 75 -> Color(0xFFFFA000); item.percentage > 0 -> Color(0xFFFF5252); else -> Color.Gray }
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
                                item.type,
                                color = Color(0xFF00BFA6),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                        Text(
                            item.name,
                            color = if (isDark) Color.White else Color.Black,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 22.sp
                        )
                        Spacer(Modifier.height(12.dp))
                        Row {
                            Text(item.tests, color = Color(0xFF9AA4AE), fontSize = 13.sp)
                            Text("  •  ", color = Color(0xFF9AA4AE))
                            Text(item.score, color = Color(0xFF9AA4AE), fontSize = 13.sp)
                        }
                    }
                    Box(Modifier.width(1.dp).height(80.dp).background(Color.Gray.copy(0.15f)))
                    Spacer(Modifier.width(20.dp))
                    CircularProgressBox(item.percentage, "Grade: ${item.grade}", statusColor)
                }
            }
        }
    }
}
