package com.basu.dental_appointment_booking.features.doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.FolderShared
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
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

// --- DUMMY DATA FOR DOCTOR'S DAY ---
enum class VisitStatus { WAITING, IN_PROGRESS, COMPLETED }

data class DailyPatient(
    val id: String,
    val patientName: String,
    val time: String,
    val treatment: String,
    val status: VisitStatus
)

val todaysSchedule = listOf(
    DailyPatient("1", "Sarah Jenkins", "09:00 AM", "General Checkup", VisitStatus.COMPLETED),
    DailyPatient("2", "Michael Thorne", "10:00 AM", "Root Canal Prep", VisitStatus.IN_PROGRESS),
    DailyPatient("3", "Emma Davis", "11:30 AM", "Teeth Whitening", VisitStatus.WAITING),
    DailyPatient("4", "James Wilson", "01:00 PM", "Braces Adjustment", VisitStatus.WAITING),
    DailyPatient("5", "Olivia Martinez", "02:30 PM", "Consultation", VisitStatus.WAITING)
)

@Composable
fun DoctorDashboardScreen(
    onCalendarClick: () -> Unit,
    onRecordsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onPatientClick: (String) -> Unit // To open patient records later
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF1E2A38), // A slightly cool, clinical dark blue/grey tint for the doctor side
                        Color.Black
                    ),
                    center = Offset(0.5f, 0.1f),
                    radius = 1500f
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // --- HEADER SECTION ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 48.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {
                        Text(
                            text = "Good Morning,",
                            style = TextStyle(color = Color.Gray, fontSize = 16.sp)
                        )
                        Text(
                            text = "Dr. Emily Chen",
                            style = TextStyle(color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                        )
                    }

                    // Notification Icon
                    IconButton(
                        onClick = { /* Handle Alerts */ },
                        modifier = Modifier.background(Color(0x1AFFFFFF), CircleShape).size(48.dp)
                    ) {
                        Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Alerts", tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // --- DAILY GLANCE STATS ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DoctorStatCard(title = "Total Patients", value = "12", modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(12.dp))
                    DoctorStatCard(title = "Completed", value = "4", modifier = Modifier.weight(1f), valueColor = Color(0xFF4CAF50))
                    Spacer(modifier = Modifier.width(12.dp))
                    DoctorStatCard(title = "Remaining", value = "8", modifier = Modifier.weight(1f))
                }
            }

            // --- TODAY'S SCHEDULE LIST ---
            Text(
                text = "Today's Schedule",
                style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 120.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(todaysSchedule) { patient ->
                    PatientAgendaCard(patient = patient, onClick = { onPatientClick(patient.id) })
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
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly, // Perfectly centered icons
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Dashboard / Home (Active)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Dashboard",
                        tint = Color.White,
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

                // Schedule / Calendar (Inactive)
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

                // Profile (Inactive)
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

// Sub-component for Top Stats
@Composable
fun DoctorStatCard(title: String, value: String, valueColor: Color = Color.White, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color(0x1AFFFFFF), RoundedCornerShape(16.dp))
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = value, style = TextStyle(color = valueColor, fontSize = 24.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = title, style = TextStyle(color = Color.Gray, fontSize = 12.sp))
        }
    }
}

// Sub-component for the Schedule List
@Composable
fun PatientAgendaCard(patient: DailyPatient, onClick: () -> Unit) {
    val (borderColor, bgColor) = when (patient.status) {
        VisitStatus.IN_PROGRESS -> Color(0xFF64B5F6) to Color(0x3364B5F6) // Highlighted Blue
        VisitStatus.COMPLETED -> Color.White.copy(alpha = 0.1f) to Color(0x0AFFFFFF) // Dimmed
        VisitStatus.WAITING -> Color.White.copy(alpha = 0.2f) to Color(0x1AFFFFFF) // Standard
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor, RoundedCornerShape(20.dp))
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Time Column
            Column(
                modifier = Modifier.width(70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = patient.time.split(" ")[0], style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold))
                Text(text = patient.time.split(" ")[1], style = TextStyle(color = Color.Gray, fontSize = 12.sp))
            }

            // Vertical Divider line
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(40.dp)
                    .background(Color.White.copy(alpha = 0.2f))
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Patient Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = patient.patientName,
                    style = TextStyle(
                        color = if (patient.status == VisitStatus.COMPLETED) Color.Gray else Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(text = patient.treatment, style = TextStyle(color = Color.Gray, fontSize = 14.sp))
            }

            // Action / Status Indicator
            when (patient.status) {
                VisitStatus.COMPLETED -> {
                    Icon(imageVector = Icons.Rounded.CheckCircle, contentDescription = "Done", tint = Color(0xFF4CAF50))
                }
                VisitStatus.IN_PROGRESS -> {
                    // Start/Action button
                    Button(
                        onClick = onClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6), contentColor = Color.Black),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text("View", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
                VisitStatus.WAITING -> {
                    IconButton(onClick = { /* Show options to start, reschedule, etc. */ }) {
                        Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "Options", tint = Color.Gray)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorDashboardPreview() {
    MaterialTheme {
        DoctorDashboardScreen(
            onCalendarClick = {},
            onRecordsClick = {},
            onProfileClick = {},
            onPatientClick = {}
        )
    }
}