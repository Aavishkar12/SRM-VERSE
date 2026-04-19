package com.example.srmverse

import androidx.compose.runtime.Composable
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

        composable("login") {
            LoginScreen(
                isDark = isDark,
                onToggleTheme = onToggleTheme,
                navController = navController
            )
        }

        composable("privacy") {
            PrivacyPolicyScreen(
                isDark = isDark,
                navController = navController
            )
        }
        composable("contact") {
            ContactUsScreen(
                isDark = isDark,
                navController = navController
            )
        }
    }
}