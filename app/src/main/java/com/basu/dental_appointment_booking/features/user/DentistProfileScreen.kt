package com.basu.dental_appointment_booking.features.user

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@Composable
fun DentistProfileScreen(
    dentistName: String = "Dr. Emily Chen", // We can pass this dynamically from the dashboard later
    specialty: String = "Orthodontist",
    onBackClick: () -> Unit,
    onBookSlotClick: () -> Unit
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
                    center = Offset(0.5f, 0.0f), // Light radiating from the top
                    radius = 1500f
                )
            )
    ) {
        // --- SCROLLABLE CONTENT ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp), // Extra padding so content isn't hidden behind the sticky bottom bar
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Navigation Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.offset(x = (-12).dp)) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Text(
                    text = "Doctor Profile",
                    style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                // Empty box to balance the Row alignment
                Box(modifier = Modifier.size(48.dp))
            }

            // Profile Image (Large)
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .shadow(20.dp, CircleShape, spotColor = Color.White)
                    .background(Color(0xFFE0E0E0), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "Doctor Image",
                    tint = Color.Gray,
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Doctor Name & Specialty
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = dentistName,
                    style = TextStyle(color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = Icons.Rounded.Verified, contentDescription = "Verified", tint = Color(0xFF4CAF50), modifier = Modifier.size(24.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = specialty,
                style = TextStyle(color = Color.White.copy(alpha = 0.7f), fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- STATISTICS ROW (Glassmorphism Cards) ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatCard(title = "Patients", value = "1.5k+", icon = Icons.Rounded.Person)
                StatCard(title = "Experience", value = "8 Yrs", icon = Icons.Rounded.Star, iconTint = Color(0xFFFFD700))
                StatCard(title = "Rating", value = "4.9", icon = Icons.Rounded.Star, iconTint = Color(0xFFFFD700))
            }

            Spacer(modifier = Modifier.height(40.dp))

            // --- ABOUT SECTION ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "About Doctor",
                    style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "$dentistName is a highly experienced $specialty dedicated to providing top-tier dental care. Specializing in advanced cosmetic and restorative procedures, she ensures every patient leaves with a confident and healthy smile.",
                    style = TextStyle(color = Color.Gray, fontSize = 15.sp, lineHeight = 24.sp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- LOCATION / CLINIC CARD ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Working Location",
                    style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0x1AFFFFFF), RoundedCornerShape(16.dp))
                        .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Rounded.LocationOn, contentDescription = "Location", tint = Color.White)
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(text = "Nandi Dental Clinic", style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "124 Dental Avenue, Mangalore, Karnataka", style = TextStyle(color = Color.Gray, fontSize = 14.sp))
                        }
                    }
                }
            }
        }

        // --- STICKY BOTTOM BAR (Book Slot Button) ---
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color(0xE6121212)) // Frosted translucent background
                .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(vertical = 24.dp, horizontal = 24.dp)
        ) {
            Button(
                onClick = onBookSlotClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(20.dp, RoundedCornerShape(16.dp), spotColor = Color.White) // Glowing effect
            ) {
                Text(
                    text = "BOOK SLOT",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                )
            }
        }
    }
}

// Sub-component for the Statistics Cards
@Composable
fun StatCard(title: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, iconTint: Color = Color.White) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .background(Color(0x1AFFFFFF), RoundedCornerShape(16.dp))
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = icon, contentDescription = title, tint = iconTint, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = title, style = TextStyle(color = Color.Gray, fontSize = 12.sp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DentistProfilePreview() {
    MaterialTheme {
        DentistProfileScreen(
            onBackClick = {},
            onBookSlotClick = {}
        )
    }
}