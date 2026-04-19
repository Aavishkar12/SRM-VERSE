package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.navigation.NavController

@Composable
fun ContactUsScreen(
    isDark: Boolean,
    navController: NavController
) {

    val bgColor = if (isDark) Color(0xFF0B1C2C) else Color.White
    val cardColor = if (isDark) Color(0xFF162638) else Color(0xFFF8FAFC)
    val textColor = if (isDark) Color.White else Color.Black
    val subText = if (isDark) Color(0xFFB0BEC5) else Color.DarkGray
    val accent = Color(0xFF00BFA6)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(12.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Back Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = accent)
                Spacer(modifier = Modifier.width(6.dp))
                Text("Back to Home", color = accent, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Card
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = cardColor
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (isDark) 6.dp else 4.dp // subtle in light
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (isDark)
                        Color.Transparent
                    else
                        Color.LightGray.copy(alpha = 0.5f) // 🔥 visible in light
                ),
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        "📩 Contact Us",
                        color = textColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "We would love to hear from you! Whether you have a question, feedback, a bug report, or just want to say hello, feel free to reach out to us through any of the channels below.",
                        color = subText,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )

                    Section("Email", "For general inquiries, technical support, or feedback, you can reach us at support@srminsider.live. We aim to respond within 24 hours.", textColor, subText)

                    Section("Bug Reports", "If you encounter any issues, provide details including steps, device, and errors. This helps us improve faster.", textColor, subText)

                    Section("Feature Requests", "Have ideas? Send us suggestions and we’ll try to include them in future updates.", textColor, subText)

                    Section("Contributing", "Interested in contributing? You can help with development, design, testing, or documentation.", textColor, subText)

                    Section("Response Times", "We respond within 24–48 hours. Mark urgent issues clearly for priority support.", textColor, subText)

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        "SRM INSIDER COMMUNITY",
                        color = subText,
                        fontSize = 10.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun Section(
    title: String,
    content: String,
    titleColor: Color,
    textColor: Color
) {
    Spacer(modifier = Modifier.height(14.dp))

    Text(
        title,
        color = titleColor,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
        content,
        color = textColor,
        fontSize = 12.sp,
        lineHeight = 18.sp
    )
}