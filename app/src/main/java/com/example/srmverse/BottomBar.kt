package com.example.srmverse

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@Composable
fun BottomBar(navController: NavController) {

    data class BottomItem(
        val route: String,
        val label: String,
        val icon: androidx.compose.ui.graphics.vector.ImageVector
    )

    val items = listOf(
        BottomItem("attendance", "Attend", Icons.Default.CheckCircle),
        BottomItem("timetable", "Timetable", Icons.Default.Schedule),
        BottomItem("feed", "Feed", Icons.Default.DynamicFeed),
        BottomItem("marks", "Marks", Icons.Default.BarChart),
        BottomItem("calendar", "Calendar", Icons.Default.CalendarMonth)
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
        tonalElevation = 8.dp,
        modifier = Modifier.height(75.dp)
    ) {

        items.forEach { item ->

            NavigationBarItem(
                selected = currentRoute == item.route,

                onClick = {
                    navController.navigate(item.route) {
                        popUpTo("attendance")
                        launchSingleTop = true
                    }
                },

                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },

                label = {
                    Text(text = item.label)
                },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF00E5FF),
                    selectedTextColor = Color(0xFF00E5FF),
                    indicatorColor = Color(0xFF00E5FF).copy(alpha = 0.15f),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}
