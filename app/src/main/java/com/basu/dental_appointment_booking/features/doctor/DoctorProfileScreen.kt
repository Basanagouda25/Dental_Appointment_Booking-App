package com.basu.dental_appointment_booking.features.doctor

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
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.FolderShared
import androidx.compose.material.icons.rounded.Group
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocalHospital
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material.icons.rounded.Verified
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
fun DoctorProfileScreen(
    onHomeClick: () -> Unit,
    onCalendarClick: () -> Unit,
    onRecordsClick: () -> Unit,
    onLogoutClick: () -> Unit // Trigger this to reset the app flow
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF1E2A38), // Clinical dark blue/grey tint
                        Color.Black
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
                text = "Clinic Settings",
                style = TextStyle(color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(start = 24.dp, top = 48.dp, bottom = 24.dp)
            )

            // --- DOCTOR INFO CARD ---
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
                    // Profile Image with Edit Badge
                    Box(contentAlignment = Alignment.BottomEnd) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .shadow(10.dp, CircleShape, spotColor = Color(0xFF64B5F6))
                                .background(Color(0xFFF0F0F0), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Rounded.Person, contentDescription = "Doctor", tint = Color.Gray, modifier = Modifier.size(40.dp))
                        }
                        // Small Edit Icon Overlaid
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .offset(x = 4.dp, y = 4.dp)
                                .background(Color(0xFF64B5F6), CircleShape)
                                .border(2.dp, Color(0xFF1E2A38), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Rounded.Edit, contentDescription = "Edit", tint = Color.Black, modifier = Modifier.size(12.dp))
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Name and License
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Dr. Emily Chen", style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold))
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(imageVector = Icons.Rounded.Verified, contentDescription = "Verified", tint = Color(0xFF64B5F6), modifier = Modifier.size(16.dp))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Orthodontist", style = TextStyle(color = Color.Gray, fontSize = 14.sp))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "License: #MD-847201", style = TextStyle(color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp, fontFamily = FontFamily.Monospace))
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // --- MANAGEMENT MENU ---
            Text(
                text = "Practice Management",
                style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding( bottom = 16.dp)
            )

            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                DoctorMenuRow(icon = Icons.Rounded.LocalHospital, title = "Clinic Profile", subtitle = "Address, Contact, Public Bio")
                DoctorMenuRow(icon = Icons.Rounded.Group, title = "Staff & Doctors", subtitle = "Manage receptionists and peers")
                DoctorMenuRow(icon = Icons.Rounded.AccountBalanceWallet, title = "Billing & Payouts", subtitle = "Bank details, Revenue history")
                DoctorMenuRow(icon = Icons.Rounded.Notifications, title = "Alerts & Reminders", subtitle = "Customize automated patient SMS")
                DoctorMenuRow(icon = Icons.Rounded.Security, title = "Security & Access", subtitle = "2FA, App lock, Data export")

                Spacer(modifier = Modifier.height(32.dp))

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
                .align(Alignment.BottomCenter)                .padding(bottom = 24.dp, start = 32.dp, end = 32.dp) // Floating effect
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
                horizontalArrangement = Arrangement.SpaceEvenly, // Perfectly centered
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Dashboard (Inactive)
                IconButton(onClick = onHomeClick) {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Dashboard",
                        tint = Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }

                // Schedule (Inactive)
                IconButton(onClick = onCalendarClick) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = "Schedule",
                        tint = Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }

                // Records (Inactive)
                IconButton(onClick = onRecordsClick) {
                    Icon(
                        imageVector = Icons.Rounded.FolderShared,
                        contentDescription = "Records",
                        tint = Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }

                // Profile (Active)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "Profile",
                        tint = Color.White, // Active color
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Active indicator dot
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(Color.White, CircleShape)
                    )
                }
            }
        }
    }
}

// Sub-component for Menu Rows
@Composable
fun DoctorMenuRow(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null, onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon Box (Using Doctor Blue Tint)
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFF64B5F6).copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = title, tint = Color(0xFF64B5F6))
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
fun DoctorProfilePreview() {
    MaterialTheme {
        DoctorProfileScreen(onHomeClick = {}, onCalendarClick = {}, onRecordsClick = {}, onLogoutClick = {})
    }
}