package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InternshipsScreen(isDark: Boolean, onMenuClick: () -> Unit, onNavigate: (String) -> Unit) {
    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val accentColor = Color(0xFF00CFE8)
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        StandardHeader("Internships", "Find your dream internship", isDark, onMenuClick, onNavigate)
        Spacer(Modifier.height(20.dp))

        // 🔹 CATEGORY HEADER
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFF00BFA6), fontWeight = FontWeight.Bold)) {
                        append("Computer Science ")
                    }
                    withStyle(style = SpanStyle(color = textSecondary)) {
                        append("Internships")
                    }
                },
                fontSize = 14.sp
            )

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(32.dp)
                    .background(chipContainerBg, CircleShape)
            ) {
                Icon(Icons.Outlined.Refresh, null, tint = accentColor, modifier = Modifier.size(18.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 SEARCH/CATEGORY DROPDOWN
        OutlinedTextField(
            value = "Computer Science",
            onValueChange = {},
            trailingIcon = {
                Icon(Icons.Default.KeyboardArrowDown, null, tint = textSecondary)
            },
            leadingIcon = {
                Icon(Icons.Default.Search, null, tint = textSecondary, modifier = Modifier.size(20.dp))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(30.dp),
            readOnly = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = chipContainerBg,
                focusedContainerColor = chipContainerBg,
                unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.1f),
                focusedIndicatorColor = accentColor.copy(alpha = 0.5f),
                cursorColor = accentColor,
                focusedTextColor = textPrimary,
                unfocusedTextColor = textPrimary
            )
        )

        Spacer(Modifier.height(20.dp))

        // 🔹 INTERNSHIPS LIST
        val internships = listOf(
            InternshipItem("Quality Assurance & Production Manager", "Assocom Foods Private Limited", "₹ 6,000 - 17,000 /MONTH", false),
            InternshipItem("Content Writing (Web3 & Defi)", "Podha Protocol", "₹ 5,000 - 10,000 /MONTH", false),
            InternshipItem("Product Management", "Adfinity Global Solutions P Limited", "₹ 9,000 - 10,000 /MONTH", true),
            InternshipItem("Information Technology", "Planys Technologies Private Limited", "₹ 10,000 - 15,000 /MONTH", false),
            InternshipItem("Intern Desktop Support", "Ancillarie LLP", "₹ 5,000 /MONTH", false)
        )

        internships.forEach { item ->
            InternshipCard(item, isDark)
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.height(100.dp))
    }
}

data class InternshipItem(val title: String, val company: String, val stipend: String, val hasJobOffer: Boolean)

@Composable
fun InternshipCard(item: InternshipItem, isDark: Boolean) {
    val cardBg = if (isDark) Color(0xFF111821) else Color.White
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val accentColor = Color(0xFF00BFA6)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Logo Placeholder
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                // In a real app, use Image with painterResource or AsyncImage
                Icon(Icons.Default.Business, null, tint = Color.Gray)
            }

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        item.title,
                        color = textPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(Icons.Default.KeyboardArrowDown, null, tint = textSecondary, modifier = Modifier.size(20.dp))
                }
                
                Text(item.company, color = textSecondary, fontSize = 13.sp)
                
                Spacer(Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (item.hasJobOffer) {
                        Surface(
                            color = accentColor.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(20.dp),
                            border = BorderStroke(1.dp, accentColor.copy(alpha = 0.2f))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Lightbulb, null, tint = accentColor, modifier = Modifier.size(12.dp))
                                Spacer(Modifier.width(4.dp))
                                Text("JOB OFFER", color = accentColor, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        Spacer(Modifier.width(8.dp))
                    }

                    Surface(
                        color = accentColor.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, accentColor.copy(alpha = 0.2f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.CurrencyRupee, null, tint = accentColor, modifier = Modifier.size(12.dp))
                            Spacer(Modifier.width(4.dp))
                            Text(item.stipend, color = accentColor, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
