package com.basu.dental_appointment_booking.features.user

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
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
data class Dentist(
    val id: String,
    val name: String,
    val specialty: String,
    val rating: String,
    val reviews: String,
    val availability: String
)

val sampleDentists = listOf(
    Dentist("1", "Dr. Emily Chen", "Orthodontist", "4.9", "120", "Available Today"),
    Dentist("2", "Dr. Marcus Thorne", "Cosmetic Dentistry", "4.8", "85", "Next Avail: Tomorrow"),
    Dentist("3", "Dr. Sarah Jenkins", "Pediatric Dentist", "5.0", "210", "Available Today"),
    Dentist("4", "Dr. Alan Grant", "Oral Surgeon", "4.7", "64", "Next Avail: Wed")
)

@Composable
fun DentistCard(
    dentist: Dentist,
    onBookClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .shadow(8.dp, RoundedCornerShape(20.dp), clip = false)
            .background(Color(0x1AFFFFFF), RoundedCornerShape(20.dp)) // Glass background
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Profile Image Placeholder
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color(0xFFF0F0F0), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "Profile",
                    tint = Color.Gray,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Dentist Details
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = dentist.name,
                    style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = dentist.specialty,
                    style = TextStyle(color = Color.Gray, fontSize = 14.sp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Rating and Availability Row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.Star, contentDescription = "Rating", tint = Color(0xFFFFD700), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${dentist.rating} (${dentist.reviews})", style = TextStyle(color = Color.LightGray, fontSize = 12.sp))

                    Spacer(modifier = Modifier.width(12.dp))

                    // Small availability indicator dot
                    Box(modifier = Modifier.size(6.dp).background(if (dentist.availability.contains("Today")) Color(0xFF4CAF50) else Color.Gray, CircleShape))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = dentist.availability, style = TextStyle(color = Color.LightGray, fontSize = 12.sp))
                }
            }

            // Book Action Button
            Button(
                onClick = {onBookClick(dentist.id)},
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text("Book", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDashboard(
    onBookClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF1E1E1E), // Deep dark gray
                        Color(0xFF000000)  // Pitch black
                    ),
                    center = Offset(0.5f, 0.2f),
                    radius = 1500f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // --- FIXED HEADER SECTION (Doesn't scroll) ---
            Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 48.dp)) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //Spacer(modifier = Modifier.height(30.dp))
                    Column {
                        Text(
                            text = "Hello, Sarah",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily.SansSerif
                            )
                        )
                        Text(
                            text = "Find your specialist today.",
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        )
                    }

                    // Notification Icon
                    IconButton(
                        onClick = { /* Handle Notifications */ },
                        modifier = Modifier
                            .background(Color(0x1AFFFFFF), CircleShape)
                            .size(48.dp)
                    ) {
                        Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Notifications", tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Sleek Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    placeholder = { Text("Search doctors, specialties...", color = Color.Gray, fontSize = 14.sp) },
                    leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = "Search", tint = Color.Gray) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0x1AFFFFFF),
                        unfocusedContainerColor = Color(0x1AFFFFFF),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Top Specialists",
                    style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // --- SCROLLABLE LIST SECTION ---
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 120.dp) // Extra bottom padding so items aren't hidden by the nav bar
            ) {
                items(sampleDentists) { dentist ->
                    DentistCard(
                        dentist = dentist,
                        onBookClick = { id -> onBookClick(id) }
                    )
                }
            }
        }

        // --- NEAT FLOATING BOTTOM NAVIGATION ---
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
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Home Tab (Active)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Home",
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(modifier = Modifier.size(4.dp).background(Color.White, CircleShape)) // Active indicator
                }

                // Bookings Tab
                IconButton(onClick = { /* Navigate */ }) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = "Bookings",
                        tint = Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }

                // Profile Tab
                IconButton(onClick = { /* Navigate */ }) {
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

@Preview(showBackground = true)
@Composable
fun PatientDashboardPreview() {
    MaterialTheme {
        PatientDashboard(onBookClick = {})
    }
}