package com.example.srmverse

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomBar(navController: NavController, isDark: Boolean) {

    data class BottomItem(
        val route: String,
        val label: String,
        val icon: androidx.compose.ui.graphics.vector.ImageVector
    )

    val items = listOf(
        BottomItem("attendance_main", "Attendance", Icons.Default.CheckCircle),
        BottomItem("timetable", "Timetable", Icons.Default.Schedule),
        BottomItem("feed", "Feed", Icons.Default.DynamicFeed),
        BottomItem("marks", "Marks", Icons.Default.BarChart),
        BottomItem("calendar", "Calendar", Icons.Default.CalendarMonth)
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val containerColor = if (isDark) Color(0xFF121821) else Color.White
    val selectedColor = Color(0xFF00E5FF)
    val unselectedColor = Color(0xFF9AA4AE)

    NavigationBar(
        containerColor = containerColor,
        tonalElevation = 0.dp,
        modifier = Modifier
            .navigationBarsPadding()
            .height(80.dp),
        windowInsets = WindowInsets(0, 0, 0, 0) // We handle padding via modifier
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
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
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = selectedColor,
                    indicatorColor = selectedColor,
                    unselectedIconColor = unselectedColor,
                    unselectedTextColor = unselectedColor
                )
            )
        }
    }
}
