package com.basu.dental_appointment_booking.features.user

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.MedicalInformation
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookingConfirmationScreen(
    // In the future, these will be passed dynamically based on what the user selected
    // or fetched from Firebase based on the Appointment ID.
    doctorName: String = "Dr. Emily Chen",
    specialty: String = "Orthodontist",
    date: String = "Wednesday, Oct 26",
    time: String = "10:00 AM",
    reason: String = "General Checkup",
    location: String = "Nandi Premium Clinic\n124 Dental Avenue, NY",
    onDoneClick: () -> Unit // Trigger this to go back to the Dashboard
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
                    center = Offset(0.5f, 0.2f),
                    radius = 1500f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 48.dp)
                .padding(bottom = 100.dp), // Space for sticky bottom button
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // --- SUCCESS GRAPHIC ---
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .shadow(30.dp, CircleShape, spotColor = Color(0xFF4CAF50)) // Glowing green shadow
                    .background(Color(0xFF4CAF50).copy(alpha = 0.1f), CircleShape) // Translucent outer ring
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF4CAF50), CircleShape), // Solid green inner circle
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Rounded.Check, contentDescription = "Success", tint = Color.White, modifier = Modifier.size(48.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- SUCCESS TEXT ---
            Text(
                text = "Booking Confirmed!",
                style = TextStyle(color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Your appointment has been successfully scheduled.",
                style = TextStyle(color = Color.Gray, fontSize = 14.sp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- APPOINTMENT DETAILS SUMMARY CARD ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(16.dp, RoundedCornerShape(24.dp), clip = false)
                    .background(Color(0x1AFFFFFF), RoundedCornerShape(24.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(24.dp))
                    .padding(24.dp)
            ) {
                Column {
                    // DOCTOR ROW
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.size(48.dp).background(Color(0xFFF0F0F0), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Rounded.Person, contentDescription = "Doctor", tint = Color.Gray)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = doctorName, style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold))
                            Text(text = specialty, style = TextStyle(color = Color.Gray, fontSize = 14.sp))
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Divider(color = Color.White.copy(alpha = 0.1f), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(20.dp))

                    // DATE & TIME
                    DetailRow(icon = Icons.Rounded.CalendarToday, label = "Date", value = date)
                    Spacer(modifier = Modifier.height(16.dp))
                    DetailRow(icon = Icons.Rounded.Schedule, label = "Time", value = time)

                    Spacer(modifier = Modifier.height(20.dp))
                    Divider(color = Color.White.copy(alpha = 0.1f), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(20.dp))

                    // REASON & LOCATION
                    DetailRow(icon = Icons.Rounded.MedicalInformation, label = "Reason", value = reason)
                    Spacer(modifier = Modifier.height(16.dp))
                    DetailRow(icon = Icons.Rounded.LocationOn, label = "Location", value = location)
                }
            }
        }

        // --- STICKY BOTTOM BUTTON ---
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color(0xE6121212))
                .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(vertical = 24.dp, horizontal = 24.dp)
        ) {
            Button(
                onClick = onDoneClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(20.dp, RoundedCornerShape(16.dp), spotColor = Color.White)
            ) {
                Text(
                    text = "BACK TO DASHBOARD",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                )
            }
        }
    }
}

// Reusable component for the detail rows
@Composable
fun DetailRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.Gray, modifier = Modifier.size(20.dp).padding(top = 2.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = label, style = TextStyle(color = Color.Gray, fontSize = 12.sp))
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = value, style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookingConfirmationPreview() {
    MaterialTheme {
        BookingConfirmationScreen(onDoneClick = {})
    }
}