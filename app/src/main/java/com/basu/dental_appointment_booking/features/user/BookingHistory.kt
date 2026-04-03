package com.basu.dental_appointment_booking.features.user

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- DUMMY DATA MODEL ---
enum class AppointmentStatus { UPCOMING, COMPLETED, CANCELLED }

data class Appointment(
    val id: String,
    val doctorName: String,
    val specialty: String,
    val date: String,
    val time: String,
    val status: AppointmentStatus
)

val sampleAppointments = listOf(
    Appointment("1", "Dr. Emily Chen", "Orthodontist", "Oct 26, 2026", "10:00 AM", AppointmentStatus.UPCOMING),
    Appointment("2", "Dr. Sarah Jenkins", "Pediatric Dentist", "Nov 12, 2026", "02:30 PM", AppointmentStatus.UPCOMING),
    Appointment("3", "Dr. Marcus Thorne", "Cosmetic Dentistry", "Sep 15, 2026", "09:00 AM", AppointmentStatus.COMPLETED),
    Appointment("4", "Dr. Alan Grant", "Oral Surgeon", "Aug 02, 2026", "11:00 AM", AppointmentStatus.CANCELLED)
)

@Composable
fun BookingHistoryScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    // State for filtering tabs
    var selectedTab by remember { mutableStateOf(AppointmentStatus.UPCOMING) }

    // Filter the list based on the selected tab
    val filteredAppointments = sampleAppointments.filter { it.status == selectedTab }

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
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            // --- HEADER ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 48.dp)
            ) {
                Text(
                    text = "My Appointments",
                    style = TextStyle(color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Track and manage your visits",
                    style = TextStyle(color = Color.Gray, fontSize = 14.sp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // --- FILTER TABS ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    FilterTab(
                        text = "Upcoming",
                        isSelected = selectedTab == AppointmentStatus.UPCOMING,
                        onClick = { selectedTab = AppointmentStatus.UPCOMING },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    FilterTab(
                        text = "Completed",
                        isSelected = selectedTab == AppointmentStatus.COMPLETED,
                        onClick = { selectedTab = AppointmentStatus.COMPLETED },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    FilterTab(
                        text = "Cancelled",
                        isSelected = selectedTab == AppointmentStatus.CANCELLED,
                        onClick = { selectedTab = AppointmentStatus.CANCELLED },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // --- APPOINTMENT LIST ---
            if (filteredAppointments.isEmpty()) {
                // Empty State
                Box(modifier = Modifier.fillMaxSize().padding(bottom = 100.dp), contentAlignment = Alignment.Center) {
                    Text(text = "No appointments found.", style = TextStyle(color = Color.Gray, fontSize = 16.sp))
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 120.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredAppointments) { appointment ->
                        AppointmentCard(appointment = appointment)
                    }
                }
            }
        }

        // --- MATCHED NEAT FLOATING BOTTOM NAVIGATION ---
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp, start = 32.dp, end = 32.dp) // Floating effect
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
                modifier = Modifier.fillMaxSize(), // Fill the box for proper alignment
                horizontalArrangement = Arrangement.SpaceEvenly, // MATCHED: Spread evenly like Dashboard
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Home Tab (Inactive)
                IconButton(onClick = onHomeClick) {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Home",
                        tint = Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }

                // Bookings Tab (Active)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = "Bookings",
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Active indicator dot to match Dashboard style
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(Color.White, CircleShape)
                    )
                }

                // Profile Tab (Inactive)
                IconButton(onClick = onProfileClick) {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "Profile",
                        tint = Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }
    }
}

// Custom Glassmorphism Tab Component
@Composable
fun FilterTab(text: String, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val bgColor by animateColorAsState(if (isSelected) Color.White else Color(0x1AFFFFFF), label = "")
    val textColor by animateColorAsState(if (isSelected) Color.Black else Color.White, label = "")

    Box(
        modifier = modifier
            .background(bgColor, RoundedCornerShape(12.dp))
            .border(1.dp, if (isSelected) Color.Transparent else Color.White.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null, onClick = onClick)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = TextStyle(color = textColor, fontSize = 14.sp, fontWeight = FontWeight.Bold))
    }
}

// Custom Appointment Card
@Composable
fun AppointmentCard(appointment: Appointment) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(20.dp), clip = false)
            .background(Color(0x1AFFFFFF), RoundedCornerShape(20.dp))
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Column {
            // Top Row: Date & Time + Status Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = appointment.date, style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = appointment.time, style = TextStyle(color = Color.Gray, fontSize = 14.sp))
                }

                // Dynamic Status Badge
                val (statusBg, statusText) = when (appointment.status) {
                    AppointmentStatus.UPCOMING -> Color(0x334CAF50) to Color(0xFF4CAF50) // Green
                    AppointmentStatus.COMPLETED -> Color(0x332196F3) to Color(0xFF64B5F6) // Blue
                    AppointmentStatus.CANCELLED -> Color(0x33F44336) to Color(0xFFE57373) // Red
                }

                Box(
                    modifier = Modifier
                        .background(statusBg, RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(text = appointment.status.name, style = TextStyle(color = statusText, fontSize = 10.sp, fontWeight = FontWeight.Bold))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.White.copy(alpha = 0.05f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Bottom Row: Doctor Details & Action
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.size(40.dp).background(Color(0xFFF0F0F0), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Rounded.Person, contentDescription = "Doctor", tint = Color.Gray, modifier = Modifier.size(24.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(text = appointment.doctorName, style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold))
                        Text(text = appointment.specialty, style = TextStyle(color = Color.Gray, fontSize = 12.sp))
                    }
                }

                // Three-dot menu for actions (Cancel/Reschedule)
                if (appointment.status == AppointmentStatus.UPCOMING) {
                    IconButton(onClick = { /* Show bottom sheet with cancel/reschedule options */ }) {
                        Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "Options", tint = Color.Gray)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookingHistoryPreview() {
    MaterialTheme {
        BookingHistoryScreen(onHomeClick = {}, onProfileClick = {}, onBackClick = {})
    }
}