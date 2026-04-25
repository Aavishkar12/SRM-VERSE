package com.example.srmverse

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(
    isDark: Boolean,
    onToggleTheme: () -> Unit
) {
    val navController: NavHostController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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

        composable("attendance") {
            val bottomNavController = rememberNavController()

            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    AppDrawer(
                        isDark = isDark,
                        onNavigate = { route ->
                            scope.launch { drawerState.close() }
                            bottomNavController.navigate(route) {
                                popUpTo(bottomNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        onClose = { scope.launch { drawerState.close() } },
                        onSignOut = {
                            scope.launch { drawerState.close() }
                            navController.navigate("login") {
                                popUpTo("attendance") { inclusive = true }
                            }
                        }
                    )
                }
            ) {
                Scaffold(
                    bottomBar = {
                        BottomBar(navController = bottomNavController, isDark = isDark)
                    }
                ) { innerPadding ->
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
                                AttendanceScreen(isDark = isDark, onMenuClick = { scope.launch { drawerState.open() } })
                            }
                            composable("timetable") {
                                TimetableScreen(isDark = isDark, onMenuClick = { scope.launch { drawerState.open() } })
                            }
                            composable("feed") {
                                FeedScreen(isDark = isDark, onMenuClick = { scope.launch { drawerState.open() } })
                            }
                            composable("marks") {
                                MarksScreen(isDark = isDark, onMenuClick = { scope.launch { drawerState.open() } })
                            }
                            composable("calendar") {
                                CalendarScreen(isDark = isDark, onMenuClick = { scope.launch { drawerState.open() } })
                            }
                            composable("elibrary") {
                                ELibraryScreen(isDark = isDark, onMenuClick = { scope.launch { drawerState.open() } })
                            }
                            composable("internships") {
                                InternshipsScreen(isDark = isDark, onMenuClick = { scope.launch { drawerState.open() } })
                            }
                        }
                    }
                }
            }
        }

        composable("privacy") {
            PrivacyPolicyScreen(isDark = isDark, navController = navController)
        }
        composable("contact") {
            ContactUsScreen(isDark = isDark, navController = navController)
        }
    }
}
