package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewQuilt
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StandardHeader(title: String, subtitle: String, isDark: Boolean) {
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
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
                    modifier = Modifier.size(40.dp).border(1.dp, Color(0xFF00BFA6).copy(alpha = 0.5f), RoundedCornerShape(10.dp)).padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.AutoMirrored.Filled.ViewQuilt, null, tint = Color(0xFF00BFA6), modifier = Modifier.size(24.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(title, color = textPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(subtitle, color = textSecondary, fontSize = 12.sp)
                }
            }
            Row {
                IconButton(onClick = {}) { Icon(Icons.Default.Notifications, null, tint = textSecondary, modifier = Modifier.size(22.dp)) }
                IconButton(onClick = {}) { Icon(Icons.Default.Settings, null, tint = textSecondary, modifier = Modifier.size(22.dp)) }
            }
        }
    }
}

@Composable
fun SegmentedChips(options: List<String>, selected: String, onSelect: (String) -> Unit, isDark: Boolean) {
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)
    val accentColor = Color(0xFF00CFE8)
    Row(
        modifier = Modifier.fillMaxWidth().height(44.dp).background(chipContainerBg, RoundedCornerShape(30.dp)).border(1.dp, Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(30.dp)).padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        options.forEach { option ->
            val isSelected = selected == option
            Box(
                modifier = Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(30.dp))
                    .background(if (isSelected) (if (isDark) Color(0xFF22364A) else Color.White) else Color.Transparent)
                    .clickable { onSelect(option) },
                contentAlignment = Alignment.Center
            ) {
                Text(option, color = if (isSelected) accentColor else Color(0xFF9AA4AE), fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium)
            }
        }
    }
}

@Composable
fun CircularProgressBox(percentage: Int, bottomText: String, statusColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.size(50.dp)) {
                drawArc(statusColor.copy(alpha = 0.1f), 0f, 360f, false, style = Stroke(width = 6f))
                drawArc(statusColor, -90f, (percentage / 100f) * 360f, false, style = Stroke(width = 6f, cap = StrokeCap.Round))
            }
            Text("${percentage}%", color = statusColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
        if (bottomText.isNotEmpty()) {
            Spacer(Modifier.height(4.dp))
            Text(bottomText, color = statusColor, fontSize = 9.sp, fontWeight = FontWeight.Bold)
        }
    }
}
