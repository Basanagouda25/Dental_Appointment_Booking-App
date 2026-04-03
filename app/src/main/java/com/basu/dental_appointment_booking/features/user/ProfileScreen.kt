package com.basu.dental_appointment_booking.features.user

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.MedicalInformation
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserProfileScreen(
    onHomeClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onLogoutClick: () -> Unit // Trigger this to go back to Login
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF222222), // Deep charcoal
                        Color(0xFF000000)  // Pitch black
                    ),
                    center = Offset(0.5f, 0.0f),
                    radius = 1500f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 120.dp) // Space for bottom nav
        ) {
            // --- HEADER TITLE ---
            Text(
                text = "Profile",
                style = TextStyle(color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(start = 24.dp, top = 48.dp, bottom = 24.dp)
            )

            // --- USER INFO CARD ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .shadow(16.dp, RoundedCornerShape(20.dp), clip = false)
                    .background(Color(0x1AFFFFFF), RoundedCornerShape(20.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(20.dp))
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Profile Image
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .shadow(10.dp, CircleShape, spotColor = Color.White)
                            .background(Color(0xFFF0F0F0), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Rounded.Person, contentDescription = "User", tint = Color.Gray, modifier = Modifier.size(40.dp))
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Name and Email
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Sarah Jenkins", style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "sarah.j@example.com", style = TextStyle(color = Color.Gray, fontSize = 14.sp))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "+1 234 567 8900", style = TextStyle(color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp))
                    }

                    // Edit Button
                    IconButton(onClick = { /* Edit Profile */ }) {
                        Icon(imageVector = Icons.Rounded.Settings, contentDescription = "Edit", tint = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // --- QUICK STATS (Health Summary) ---
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                QuickStatCard(title = "Blood Group", value = "O+", modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(16.dp))
                QuickStatCard(title = "Visits", value = "12", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(40.dp))

            // --- MENU OPTIONS ---
            Text(
                text = "Settings",
                style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 16.dp)
            )

            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                ProfileOptionRow(icon = Icons.Rounded.MedicalInformation, title = "Dental Records", subtitle = "X-Rays, Prescriptions")
                ProfileOptionRow(icon = Icons.Rounded.CreditCard, title = "Payment Methods", subtitle = "Cards, Insurance")
                ProfileOptionRow(icon = Icons.Rounded.Notifications, title = "Notifications", subtitle = "Reminders, Updates")
                ProfileOptionRow(icon = Icons.Rounded.Security, title = "Privacy & Security", subtitle = "Passwords, Biometrics")
                ProfileOptionRow(icon = Icons.Rounded.Favorite, title = "Help & Support", subtitle = "FAQs, Contact Clinic")

                Spacer(modifier = Modifier.height(24.dp))

                // Logout Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null, onClick = onLogoutClick)
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(48.dp).background(Color(0x1AF44336), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Rounded.Logout, contentDescription = "Logout", tint = Color(0xFFE57373))
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Log Out", style = TextStyle(color = Color(0xFFE57373), fontSize = 16.sp, fontWeight = FontWeight.Bold))
                }
            }
        }

        // --- MATCHED NEAT FLOATING BOTTOM NAVIGATION ---
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp, start = 32.dp, end = 32.dp)
                .fillMaxWidth()
                .height(72.dp)
                .shadow(20.dp, RoundedCornerShape(32.dp), ambientColor = Color.Black)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF2C2C2C), Color(0xFF1A1A1A))
                    ),
                    shape = RoundedCornerShape(32.dp)
                )
                .border(0.5.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(32.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onHomeClick) {
                    Icon(imageVector = Icons.Rounded.Home, contentDescription = "Home", tint = Color.Gray, modifier = Modifier.size(26.dp))
                }

                IconButton(onClick = onHistoryClick) {
                    Icon(imageVector = Icons.Rounded.CalendarMonth, contentDescription = "Bookings", tint = Color.Gray, modifier = Modifier.size(26.dp))
                }

                // Profile Tab (Active)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "Profile",
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(modifier = Modifier.size(4.dp).background(Color.White, CircleShape))
                }
            }
        }
    }
}

// Sub-component for Quick Stats (Blood group, visits)
@Composable
fun QuickStatCard(title: String, value: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color(0x1AFFFFFF), RoundedCornerShape(16.dp))
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = value, style = TextStyle(color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = title, style = TextStyle(color = Color.Gray, fontSize = 12.sp))
        }
    }
}

// Sub-component for Menu Rows
@Composable
fun ProfileOptionRow(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null, onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon Box
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0x1AFFFFFF), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = title, tint = Color.White)
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Text
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold))
            Text(text = subtitle, style = TextStyle(color = Color.Gray, fontSize = 12.sp))
        }

        // Chevron
        Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = "Go", tint = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    MaterialTheme {
        UserProfileScreen(onHomeClick = {}, onHistoryClick = {}, onLogoutClick = {})
    }
}