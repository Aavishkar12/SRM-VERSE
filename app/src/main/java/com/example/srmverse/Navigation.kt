package com.example.srmverse

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

@Composable
fun AppNavigation(
    isDark: Boolean,
    onToggleTheme: () -> Unit
) {

    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // 🔐 LOGIN
        composable("login") {
            LoginScreen(
                isDark = isDark,
                onToggleTheme = onToggleTheme,
                navController = navController
            )
        }

        // 📊 ATTENDANCE + BOTTOM NAV
        composable("attendance") {

            val bottomNavController = rememberNavController()

            Scaffold(
                containerColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA),
                bottomBar = {
                    BottomBar(navController = bottomNavController, isDark = isDark)
                }
            ) { innerPadding ->
                // We apply only the top padding to respect the status bar/system top insets
                // while allowing the content to flow behind the floating bottom bar as intended
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding())
                ) {
                    NavHost(
                        navController = bottomNavController,
                        startDestination = "attendance_main",
                        modifier = Modifier.fillMaxSize()
                    ) {

                        composable("attendance_main") {
                            AttendanceScreen(isDark = isDark)
                        }

                        composable("timetable") {
                            TimetableScreen(isDark = isDark)
                        }

                        composable("feed") {
                            FeedScreen(isDark = isDark)
                        }

                        composable("marks") {
                            MarksScreen(isDark = isDark)
                        }

                        composable("calendar") {
                            CalendarScreen(isDark = isDark)
                        }
                    }
                }
            }
        }

        // 📜 PRIVACY
        composable("privacy") {
            PrivacyPolicyScreen(
                isDark = isDark,
                navController = navController
            )
        }

        // 📞 CONTACT
        composable("contact") {
            ContactUsScreen(
                isDark = isDark,
                navController = navController
            )
        }
    }
}