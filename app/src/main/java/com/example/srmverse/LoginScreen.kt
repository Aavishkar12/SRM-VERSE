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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale

@Composable
fun LoginScreen(
    isDark: Boolean,
    onToggleTheme: () -> Unit
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

            // 🔹 Heading (CENTERED)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Everything SRM",
                    color = textColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "One Place",
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
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Welcome Back",
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = "Sign in with your Academia credentials",
                        color = subTextColor,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("NetID or Email", color = textColor, fontSize = 12.sp)

                    Spacer(modifier = Modifier.height(6.dp))

                    var email by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("ab1234@srmist.edu.in") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = fieldColor,
                            focusedContainerColor = fieldColor,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            cursorColor = textColor,
                            unfocusedTextColor = textColor,
                            focusedTextColor = textColor
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text("Password", color = textColor, fontSize = 12.sp)

                    Spacer(modifier = Modifier.height(6.dp))

                    var password by remember { mutableStateOf("") }
                    var visible by remember { mutableStateOf(false) }

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Enter your password") },
                        singleLine = true,
                        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { visible = !visible }) {
                                Icon(
                                    imageVector = if (visible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = null,
                                    tint = subTextColor
                                )
                            }
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = fieldColor,
                            focusedContainerColor = fieldColor,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            cursorColor = textColor,
                            unfocusedTextColor = textColor,
                            focusedTextColor = textColor
                        )
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1ABC9C))
                    ) {
                        Text("Sign in →", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, subTextColor)
                    ) {
                        Icon(Icons.Default.Fingerprint, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Sign in with Passkey", color = textColor)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider(color = subTextColor.copy(alpha = 0.3f))

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("🔒 SECURED", color = subTextColor, fontSize = 11.sp)
                        Text(
                            text = "End-to-end encrypted · Your data stays private",
                            color = subTextColor,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Home", color = Color(0xFF00BFA6))
                Text("Privacy Policy", color = subTextColor)
                Text("Contact Us", color = subTextColor)
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "SRM INSIDER COMMUNITY",
                color = subTextColor,
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}