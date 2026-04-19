package com.example.srmverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.material3.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var isDark by remember { mutableStateOf(true) }

            MaterialTheme(
                colorScheme = if (isDark) darkColorScheme() else lightColorScheme()
            ) {

                AppNavigation(
                    isDark = isDark,
                    onToggleTheme = { isDark = !isDark }
                )
            }
        }
    }
}