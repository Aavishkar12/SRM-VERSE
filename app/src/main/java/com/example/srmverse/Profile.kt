package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(isDark: Boolean, onMenuClick: () -> Unit, onNavigate: (String) -> Unit, onSignOut: () -> Unit) {
    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val cardBg = if (isDark) Color(0xFF162638) else Color.White
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val tealColor = Color(0xFF00BFA6)
    val sectionHeaderColor = Color(0xFF9AA4AE)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(8.dp))

        // 🔹 HEADER
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = cardBg),
            elevation = CardDefaults.cardElevation(if (isDark) 4.dp else 2.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .border(1.dp, tealColor.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                            .clickable { onMenuClick() }
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.GridView, null, tint = tealColor, modifier = Modifier.size(24.dp))
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("Profile", color = textPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Your profile", color = textSecondary, fontSize = 12.sp)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { onNavigate("feed") }) { Icon(Icons.Default.AutoAwesome, null, tint = textSecondary, modifier = Modifier.size(20.dp)) }
                    IconButton(onClick = {}) { Icon(Icons.Default.Notifications, null, tint = textSecondary, modifier = Modifier.size(22.dp)) }
                    IconButton(onClick = {}) { Icon(Icons.Default.Settings, null, tint = textSecondary, modifier = Modifier.size(22.dp)) }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // 🔹 PROFILE MAIN CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = cardBg),
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.1f))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Profile Image Placeholder
                    Box(modifier = Modifier.size(80.dp)) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.Gray.copy(alpha = 0.3f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = textSecondary
                            )
                        }
                        // Status Indicator
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF00C853))
                                .border(2.dp, cardBg, CircleShape)
                                .align(Alignment.BottomEnd)
                                .offset(x = (-2).dp, y = (-2).dp)
                        )
                    }

                    Spacer(Modifier.width(20.dp))

                    Column {
                        Text(
                            "AAVISHKAR SINGH",
                            color = textPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "RA2511003011024",
                            color = textSecondary,
                            fontSize = 14.sp
                        )
                        Spacer(Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Surface(
                                color = tealColor,
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    "B.Tech",
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Surface(
                                color = Color.Gray.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    "Sem 2",
                                    color = textSecondary,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                // Sign Out Button
                OutlinedButton(
                    onClick = onSignOut,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFEF5350)
                    ),
                    border = BorderStroke(1.dp, Color(0xFFEF5350).copy(alpha = 0.3f))
                ) {
                    Icon(Icons.AutoMirrored.Filled.Logout, null, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Sign Out", fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // 🔹 STUDENT INFORMATION SECTION
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Badge, null, tint = tealColor, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text(
                "STUDENT INFORMATION",
                color = sectionHeaderColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }

        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = cardBg),
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.1f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoRow("REG. NUMBER", "RA2511003011024", Icons.Default.Badge, isDark)
                InfoRow("PROGRAM", "B.Tech", Icons.Default.Book, isDark)
                InfoRow("SEMESTER", "Semester 2", Icons.Default.Layers, isDark)
                InfoRow("BATCH", "Batch 1/1", Icons.Default.Tag, isDark)
                InfoRow("CLASSROOM", "Room 515", Icons.Default.LocationOn, isDark)
                InfoRow("MOBILE", "9818867459", Icons.Default.Phone, isDark)
                InfoRow("EMAIL", "As9261", Icons.Default.Email, isDark)
            }
        }

        Spacer(Modifier.height(24.dp))

        // 🔹 YOUR ADVISORS SECTION
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Groups, null, tint = tealColor, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text(
                "YOUR ADVISORS",
                color = sectionHeaderColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }

        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = cardBg),
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.1f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                AdvisorSection("FACULTY ADVISOR", "Dr.Menagadevi M", "menagadm@srmist.edu.in", "9600557356", "DM", isDark)
                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = Color.Gray.copy(alpha = 0.1f))
                AdvisorSection("ACADEMIC ADVISOR", "Dr.Santhanakrishnan C", "santhanc@srmist.edu.in", "9042962822", "DC", isDark)
            }
        }

        Spacer(Modifier.height(100.dp))
    }
}

@Composable
fun InfoRow(label: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, isDark: Boolean) {
    val tealColor = Color(0xFF00BFA6)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(tealColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = tealColor, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(16.dp))
        Column {
            Text(label, color = Color(0xFF9AA4AE), fontSize = 10.sp, fontWeight = FontWeight.Bold)
            Text(value, color = if (isDark) Color.White else Color.Black, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun AdvisorSection(role: String, name: String, email: String, phone: String, initials: String, isDark: Boolean) {
    val tealColor = Color(0xFF00BFA6)
    Column {
        Text(role, color = tealColor, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(tealColor.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Text(initials, color = Color.White, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(16.dp))
            Column {
                Text(name, color = if (isDark) Color.White else Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Email, null, tint = tealColor, modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(email, color = Color(0xFF9AA4AE), fontSize = 12.sp)
                    Spacer(Modifier.width(12.dp))
                    Icon(Icons.Default.Phone, null, tint = tealColor, modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(phone, color = Color(0xFF9AA4AE), fontSize = 12.sp)
                }
            }
        }
    }
}
