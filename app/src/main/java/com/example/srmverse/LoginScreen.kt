package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(
    isDark: Boolean,
    onToggleTheme: () -> Unit,
    navController: NavHostController
) {

    val bgGradient = Brush.verticalGradient(
        colors = if (isDark) {
            listOf(Color(0xFF0B1C2C), Color(0xFF081423))
        } else {
            listOf(Color(0xFFEAF3FF), Color(0xFFFFFFFF))
        }
    )

    val textColor = if (isDark) Color.White else Color.Black
    val subTextColor = if (isDark) Color.Gray else Color.DarkGray
    val cardColor = if (isDark) Color(0xFF162638) else Color.White
    val fieldColor = if (isDark) Color(0xFF22364A) else Color(0xFFF1F5F9)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgGradient)
            .systemBarsPadding()
            .padding(horizontal = 12.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // 🔥 HEADER
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "SRM VERSE",
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }

                    IconButton(onClick = { onToggleTheme() }) {
                        Icon(
                            imageVector = if (isDark) Icons.Default.DarkMode else Icons.Default.LightMode,
                            contentDescription = null,
                            tint = if (isDark) Color.Yellow else Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 🔹 Center Heading
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Everything SRM",
                    color = textColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "One Place",
                    color = Color(0xFF00BFA6),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔹 Login Card
            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {

                Column(modifier = Modifier.padding(20.dp)) {

                    Text(
                        "Welcome Back",
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Text(
                        "Sign in with your Academia credentials",
                        color = subTextColor,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("NetID or Email", color = textColor, fontSize = 12.sp)

                    var email by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("ab1234@srmist.edu.in") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = fieldColor,
                            focusedContainerColor = fieldColor,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            cursorColor = textColor,
                            focusedTextColor = textColor,
                            unfocusedTextColor = textColor
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text("Password", color = textColor, fontSize = 12.sp)

                    var password by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Enter your password") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = fieldColor,
                            focusedContainerColor = fieldColor,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            cursorColor = textColor,
                            focusedTextColor = textColor,
                            unfocusedTextColor = textColor
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1ABC9C))
                    ) {
                        Text("Sign in →", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // 🔥 FOOTER NAV
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Home", color = Color(0xFF00BFA6), fontWeight = FontWeight.Bold)

                Text(
                    "Privacy Policy",
                    color = subTextColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate("privacy")
                    }
                )

                Text(
                    text = "Contact Us",
                    color = subTextColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate("contact")
                    }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                "SRM INSIDER COMMUNITY",
                color = subTextColor,
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}