package com.example.srmverse

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController, isDark: Boolean) {

    data class BottomItem(
        val route: String,
        val label: String,
        val icon: androidx.compose.ui.graphics.vector.ImageVector
    )

    // Icons reordered and updated exactly as per screenshot
    val items = listOf(
        BottomItem("feed", "Home", Icons.Default.GridView),
        BottomItem("attendance_main", "Attend", Icons.Default.Assignment),
        BottomItem("timetable", "Timetable", Icons.Default.Schedule),
        BottomItem("marks", "Marks", Icons.Default.BarChart),
        BottomItem("calendar", "Calendar", Icons.Default.CalendarMonth)
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val barBgColor = if (isDark) Color(0xFF161C24).copy(alpha = 0.9f) else Color.White.copy(alpha = 0.9f)
    val selectedColor = Color(0xFF00CFE8)
    val unselectedColor = Color(0xFF9AA4AE)
    val itemBgSelected = if (isDark) Color(0xFF22364A) else Color(0xFFEDF2F7)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            color = barBgColor,
            shape = RoundedCornerShape(24.dp),
            shadowElevation = 8.dp,
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.15f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { item ->
                    val isSelected = currentRoute == item.route
                    
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .padding(horizontal = 4.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) itemBgSelected else Color.Transparent)
                            .clickable {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                modifier = Modifier.size(22.dp),
                                tint = if (isSelected) selectedColor else unselectedColor
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = item.label,
                                fontSize = 10.sp,
                                color = if (isSelected) selectedColor else unselectedColor,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}
