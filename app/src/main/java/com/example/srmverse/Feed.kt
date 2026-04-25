package com.example.srmverse

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewQuilt
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeedScreen(isDark: Boolean) {

    val bgColor = if (isDark) Color(0xFF0B0F14) else Color(0xFFF5F7FA)
    val textSecondary = Color(0xFF9AA4AE)
    val accentColor = Color(0xFF00CFE8)
    val chipContainerBg = if (isDark) Color(0xFF1A222B) else Color(0xFFEDEFF2)

    var selectedFilter by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(8.dp))
        StandardHeader("Feed", "Campus community feed", isDark)
        Spacer(Modifier.height(12.dp))

        // 🔹 SUMMARY ROW
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = accentColor, fontWeight = FontWeight.Bold)) {
                        append("13 ")
                    }
                    withStyle(style = SpanStyle(color = textSecondary)) {
                        append("posts")
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

        // 🔹 FILTER & NEW POST ROW
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(0.65f) // Give chips specific weight for better balance
                    .height(44.dp)
                    .background(chipContainerBg, RoundedCornerShape(30.dp))
                    .border(1.dp, Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(30.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("All", "Help", "Academics").forEach { filter ->
                    val isSelected = selectedFilter == filter
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(30.dp))
                            .background(if (isSelected) (if (isDark) Color(0xFF22364A) else Color.White) else Color.Transparent)
                            .clickable { selectedFilter = filter },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = filter,
                            color = if (isSelected) accentColor else textSecondary,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BFA6)),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .weight(0.35f) // Balance with chips
                    .height(44.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                Icon(Icons.Default.Add, null, tint = Color.Black, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(4.dp))
                Text("New Post", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 FEED LIST
        val feedItems = listOf(
            FeedItem(
                "ANUJ TIWARI", "14d ago", "PLACEMENT",
                "Microsoft Software Engineering Explore Internship",
                "Qualification : Bachelor's degree\nPassing Year : 2028\nLocation : Bengaluru, Hyderabad, Pune, Gurgaon, Mumbai\nSalary : 1 Lakhs+/Month (Stipend)\nLink is attached below",
                66, 2, 9, true
            ),
            FeedItem(
                "ANUJ TIWARI", "17d ago", "PLACEMENT",
                "Associate Software Engineer - Systems (Platform) - Linux, Python, C++",
                "Nasuni Hiring Associate Software Engineer:\nGraduation Year: 2023 / 2024 / 2025 /2026\nLocation: Hyderabad\nApply Link:",
                15, 1, 0, false
            ),
            FeedItem(
                "R.SOUNDARYA", "20d ago", "HELP",
                "Anyone Shortlisted for Amadeus",
                "Please send the excel",
                8, 5, 0, false
            ),
            FeedItem(
                "J SHRIPAD", "1d ago", "PLACEMENT",
                "Hiring",
                "Looking for some UI/UX designers, stipend upto 2k/month with letter of recommendation, contact me on whatsapp, link below",
                0, 0, 0, false
            )
        )

        feedItems.forEach { item ->
            FeedCard(item, isDark)
            Spacer(Modifier.height(16.dp))
        }

        Spacer(Modifier.height(100.dp))
    }
}

data class FeedItem(
    val author: String,
    val time: String,
    val tag: String,
    val title: String,
    val content: String,
    val upvotes: Int,
    val downvotes: Int,
    val comments: Int,
    val hasImage: Boolean
)

@Composable
fun FeedCard(item: FeedItem, isDark: Boolean) {
    val cardBg = if (isDark) Color(0xFF111821) else Color.White
    val textPrimary = if (isDark) Color.White else Color.Black
    val textSecondary = Color(0xFF9AA4AE)
    val tagColor = if (item.tag == "PLACEMENT") Color(0xFF673AB7) else Color(0xFF00BFA6)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Brush.linearGradient(listOf(Color(0xFF673AB7), Color(0xFF9C27B0))), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        val initials = item.author.split(" ").mapNotNull { it.firstOrNull() }.joinToString("").take(2)
                        Text(initials, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(item.author, color = textPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(item.time, color = textSecondary, fontSize = 11.sp)
                    }
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = tagColor.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            item.tag,
                            color = tagColor,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Default.MoreHoriz, null, tint = textSecondary)
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = item.title,
                color = textPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 22.sp
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = item.content,
                color = textSecondary,
                fontSize = 13.sp,
                lineHeight = 20.sp
            )

            if (item.hasImage) {
                Spacer(Modifier.height(12.dp))
                // Mocking the Microsoft Logo from screenshot
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.2f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1A1A1A))
                        .padding(24.dp)
                ) {
                    Column(Modifier.fillMaxSize()) {
                        Row(Modifier.weight(1f).fillMaxWidth()) {
                            Box(Modifier.weight(1f).fillMaxHeight().padding(4.dp).background(Color(0xFFF25022)))
                            Box(Modifier.weight(1f).fillMaxHeight().padding(4.dp).background(Color(0xFF7FBA00)))
                        }
                        Row(Modifier.weight(1f).fillMaxWidth()) {
                            Box(Modifier.weight(1f).fillMaxHeight().padding(4.dp).background(Color(0xFF00A4EF)))
                            Box(Modifier.weight(1f).fillMaxHeight().padding(4.dp).background(Color(0xFFFFB900)))
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Footer Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.05f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {}) { Icon(Icons.Default.ArrowUpward, null, tint = textSecondary, modifier = Modifier.size(18.dp)) }
                    Text(item.upvotes.toString(), color = textSecondary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    IconButton(onClick = {}) { Icon(Icons.Default.ArrowDownward, null, tint = textSecondary, modifier = Modifier.size(18.dp)) }
                }

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, Color(0xFF00BFA6).copy(alpha = 0.3f)),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.height(36.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Icon(Icons.Outlined.Link, null, tint = Color(0xFF00BFA6), modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Link", color = Color(0xFF00BFA6), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }

                Row(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.05f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.ChatBubbleOutline, null, tint = textSecondary, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(item.comments.toString(), color = textSecondary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
