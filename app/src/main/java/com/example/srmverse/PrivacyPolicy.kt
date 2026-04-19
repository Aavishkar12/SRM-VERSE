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
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun PrivacyPolicyScreen(
    isDark: Boolean,
    navController: NavController
) {

    val bgColor = if (isDark) Color(0xFF0B1C2C) else Color(0xFFFFFFFF)
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
                .padding(top = 20.dp) // 🔥 shift down
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            // 🔹 Back Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
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
                        "📄 Privacy Policy",
                        color = textColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        "Last Updated: February 26, 2026",
                        color = subText,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    PolicySection(
                        "1. Information We Collect",
                        "SRM VERSE collects only the information necessary to provide a seamless academic experience. When you log in, your SRM Academia credentials are used to authenticate with official servers. We do not store your password. Session tokens are temporarily used to fetch data like attendance, marks, timetable, and calendar.",
                        textColor,
                        subText
                    )

                    PolicySection(
                        "2. How We Use Your Information",
                        "Your data is used only to display academic information in a clean and user-friendly interface. We act as a bridge between you and the SRM Academia portal. We do not sell, trade, or share your personal data with third parties.",
                        textColor,
                        subText
                    )

                    PolicySection(
                        "3. Data Security",
                        "We use industry-standard security practices. All communication is encrypted using HTTPS/TLS protocols. Session tokens are handled securely and are never exposed to unauthorized parties.",
                        textColor,
                        subText
                    )

                    PolicySection(
                        "4. Data Retention",
                        "We do not store your academic data permanently. All information is fetched in real-time from SRM Academia. Once your session ends, all associated data is cleared.",
                        textColor,
                        subText
                    )

                    PolicySection(
                        "5. Third-Party Services",
                        "SRM VERSE interacts only with official SRM Academia systems. We do not integrate third-party analytics, ads, or tracking services. Your activity is not monitored or shared.",
                        textColor,
                        subText
                    )

                    PolicySection(
                        "6. Your Rights",
                        "You have the right to access, update, or delete your personal data. You can stop using the service anytime. If you have concerns, you may contact us through the Contact Us section.",
                        textColor,
                        subText
                    )

                    PolicySection(
                        "7. Changes to This Policy",
                        "We may update this privacy policy occasionally. Any updates will be reflected on this page with a revised date. Please review it periodically.",
                        textColor,
                        subText
                    )

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
fun PolicySection(
    title: String,
    content: String,
    titleColor: Color,
    textColor: Color
) {
    Spacer(modifier = Modifier.height(12.dp))

    Text(
        text = title,
        color = titleColor,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
        text = content,
        color = textColor,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        textAlign = TextAlign.Start
    )
}