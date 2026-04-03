package com.basu.dental_appointment_booking.features.dashboard

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Block
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.FolderShared
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basu.dental_appointment_booking.features.user.DateSlot

// --- DUMMY DATA MODELS ---
enum class SlotStatus { AVAILABLE, BOOKED, BLOCKED }

data class TimeSlot(
    val time: String,
    val status: SlotStatus,
    val patientName: String? = null,
    val treatment: String? = null,
    val note: String? = null
)

val doctorWeeklyDates = listOf(
    DateSlot("Mon", "24"), DateSlot("Tue", "25"), DateSlot("Wed", "26"),
    DateSlot("Thu", "27"), DateSlot("Fri", "28")
)

val dailyTimeline = listOf(
    TimeSlot("08:00 AM", SlotStatus.AVAILABLE),
    TimeSlot("09:00 AM", SlotStatus.BOOKED, "Sarah Jenkins", "General Checkup"),
    TimeSlot("10:00 AM", SlotStatus.BOOKED, "Michael Thorne", "Root Canal Prep"),
    TimeSlot("11:00 AM", SlotStatus.AVAILABLE),
    TimeSlot("12:00 PM", SlotStatus.BLOCKED, note = "Lunch Break"),
    TimeSlot("01:00 PM", SlotStatus.BLOCKED, note = "Team Meeting"),
    TimeSlot("02:00 PM", SlotStatus.BOOKED, "Emma Davis", "Teeth Whitening"),
    TimeSlot("03:00 PM", SlotStatus.AVAILABLE)
)

@Composable
fun DoctorScheduleScreen(
    onHomeClick: () -> Unit,
    onRecordsClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    var selectedDateIndex by remember { mutableStateOf(1) } // Default to "Today" (Tue 25)

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
            modifier = Modifier.fillMaxSize()
        ) {
            // --- HEADER ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 48.dp)
            ) {
                Text(
                    text = "Schedule Manager",
                    style = TextStyle(color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "April 2026",
                    style = TextStyle(color = Color(0xFF64B5F6), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
            }

            // --- DATE SELECTOR (Horizontal Scroll) ---
            LazyRow(
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(doctorWeeklyDates) { index, date ->
                    val isSelected = selectedDateIndex == index
                    DateCard(date = date, isSelected = isSelected) { selectedDateIndex = index }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- TIMELINE LIST ---
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 120.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(dailyTimeline) { slot ->
                    TimelineSlotCard(slot = slot)
                }
            }
        }

        // --- DOCTOR BOTTOM NAVIGATION ---
        Box(
            // --- MATCHED NEAT FLOATING BOTTOM NAVIGATION ---        Box(
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
                horizontalArrangement = Arrangement.SpaceEvenly, // Perfectly spaced icons
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Dashboard / Home (Inactive)
                IconButton(onClick = onHomeClick) {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Dashboard",
                        tint = Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }

                // Schedule / Calendar (Active)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = "Schedule",
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Active indicator dot to match your design style
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(Color.White, CircleShape)
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

// Sub-component for the Timeline Slots
@Composable
fun TimelineSlotCard(slot: TimeSlot) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        // Time Column
        Text(
            text = slot.time,
            style = TextStyle(color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.width(75.dp).padding(top = 16.dp)
        )

        // The Card
        Box(
            modifier = Modifier
                .weight(1f)
                .background(
                    color = when (slot.status) {
                        SlotStatus.BOOKED -> Color(0x1AFFFFFF)
                        SlotStatus.BLOCKED -> Color(0x0AFFFFFF)
                        SlotStatus.AVAILABLE -> Color.Transparent
                    },
                    shape = RoundedCornerShape(16.dp)
                )
                .then(
                    // If available, draw a dashed border instead of a solid one
                    if (slot.status == SlotStatus.AVAILABLE) {
                        Modifier.drawBehind {
                            drawRoundRect(
                                color = Color.Gray.copy(alpha = 0.5f),
                                style = androidx.compose.ui.graphics.drawscope.Stroke(
                                    width = 2f,
                                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                                ),
                                cornerRadius = androidx.compose.ui.geometry.CornerRadius(16.dp.toPx())
                            )
                        }
                    } else {
                        Modifier.border(
                            width = 1.dp,
                            color = if (slot.status == SlotStatus.BOOKED) Color(0xFF64B5F6).copy(alpha = 0.5f) else Color.Transparent,
                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                )
                .clickable { /* Toggle Blocked/Available or View Appointment Details */ }
                .padding(16.dp)
        ) {
            when (slot.status) {
                SlotStatus.BOOKED -> {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(40.dp).background(Color(0xFF64B5F6).copy(alpha = 0.2f), CircleShape), contentAlignment = Alignment.Center) {
                            Icon(Icons.Rounded.Person, contentDescription = "Patient", tint = Color(0xFF64B5F6))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = slot.patientName ?: "", style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold))
                            Text(text = slot.treatment ?: "", style = TextStyle(color = Color.Gray, fontSize = 12.sp))
                        }
                    }
                }
                SlotStatus.BLOCKED -> {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.Block, contentDescription = "Blocked", tint = Color.Gray, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = slot.note ?: "Blocked", style = TextStyle(color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Medium))
                    }
                }
                SlotStatus.AVAILABLE -> {
                    Text(text = "+ Available to book", style = TextStyle(color = Color(0xFF4CAF50), fontSize = 14.sp, fontWeight = FontWeight.Medium))
                }
            }
        }
    }
}

// Sub-component for Date Cards (Horizontal Calendar)
@Composable
fun DateCard(date: DateSlot, isSelected: Boolean, onClick: () -> Unit) {
    val bgColor by animateColorAsState(if (isSelected) Color.White else Color(0x1AFFFFFF), label = "bgColor")
    val textColor by animateColorAsState(if (isSelected) Color.Black else Color.White, label = "textColor")
    val subTextColor by animateColorAsState(if (isSelected) Color.DarkGray else Color.Gray, label = "subTextColor")

    Box(
        modifier = Modifier
            .width(70.dp)
            .height(90.dp)
            .background(bgColor, RoundedCornerShape(20.dp))
            .border(
                width = 1.dp,
                color = if (isSelected) Color.Transparent else Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = date.dayOfWeek,
                style = TextStyle(color = subTextColor, fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = date.dayOfMonth,
                style = TextStyle(color = textColor, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorSchedulePreview() {
    MaterialTheme {
        DoctorScheduleScreen(onHomeClick = {}, onRecordsClick = {}, onProfileClick = {})
    }
}