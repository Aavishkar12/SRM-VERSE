package com.example.srmverse

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.navigation.NavHostController



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
                bottomBar = {
                    BottomBar(navController = bottomNavController, isDark = isDark)
                }
            ) { padding ->

                NavHost(
                    navController = bottomNavController,
                    startDestination = "attendance_main",
                    modifier = Modifier.padding(padding)
                ) {

                    composable("attendance_main") {
                        AttendanceScreen(isDark = isDark)
                    }

                    composable("timetable") {
                        TimetableScreen(isDark = isDark)
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