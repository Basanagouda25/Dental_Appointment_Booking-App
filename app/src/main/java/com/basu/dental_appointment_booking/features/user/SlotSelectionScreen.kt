package com.basu.dental_appointment_booking.features.user

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
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

// --- DUMMY DATA MODELS ---
data class DateSlot(val dayOfWeek: String, val dayOfMonth: String)

val upcomingDates = listOf(
    DateSlot("Mon", "24"), DateSlot("Tue", "25"), DateSlot("Wed", "26"),
    DateSlot("Thu", "27"), DateSlot("Fri", "28"), DateSlot("Sat", "29")
)

val morningSlots = listOf("09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM")
val afternoonSlots = listOf("01:00 PM", "01:30 PM", "02:00 PM", "03:00 PM", "04:30 PM")
val visitReasons = listOf("General Checkup", "Teeth Cleaning", "Toothache / Pain", "Whitening", "Consultation")

@OptIn(ExperimentalLayoutApi::class) // Required for FlowRow
@Composable
fun BookSlotScreen(
    onBackClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    // State to hold user selections
    var selectedDateIndex by remember { mutableStateOf(0) }
    var selectedTime by remember { mutableStateOf<String?>(null) }
    var selectedReason by remember { mutableStateOf<String?>(null) }

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
                .padding(bottom = 120.dp) // Space for sticky bottom button
        ) {
            // --- TOP APP BAR ---
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
                    text = "Book Appointment",
                    style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Box(modifier = Modifier.size(48.dp)) // Spacer to balance Row
            }

            // --- DOCTOR SUMMARY ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(Color(0x1AFFFFFF), RoundedCornerShape(16.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(50.dp).background(Color(0xFFF0F0F0), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Rounded.Person, contentDescription = "Doctor", tint = Color.Gray)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "Dr. Emily Chen", style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold))
                    Text(text = "Orthodontist", style = TextStyle(color = Color.Gray, fontSize = 14.sp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- DATE SELECTION ---
            Text(
                text = "Select Date",
                style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(upcomingDates) { index, date ->
                    val isSelected = selectedDateIndex == index
                    DateCard(date = date, isSelected = isSelected) { selectedDateIndex = index }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- TIME SELECTION ---
            Text(
                text = "Available Time",
                style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Morning", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(8.dp))

            // FlowRow automatically wraps items to the next line if they don't fit
            FlowRow(
                modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                morningSlots.forEach { time ->
                    TimeChip(time = time, isSelected = selectedTime == time) { selectedTime = time }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Afternoon", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                afternoonSlots.forEach { time ->
                    TimeChip(time = time, isSelected = selectedTime == time) { selectedTime = time }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- REASON FOR VISIT (Value Add) ---
            Text(
                text = "Reason for Visit",
                style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            FlowRow(
                modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                visitReasons.forEach { reason ->
                    TimeChip(time = reason, isSelected = selectedReason == reason) { selectedReason = reason }
                }
            }
        }

        // --- STICKY BOTTOM CONFIRM BUTTON ---
        val isReadyToBook = selectedTime != null && selectedReason != null

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
                onClick = { if (isReadyToBook) onConfirmClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.White.copy(alpha = 0.2f),
                    disabledContentColor = Color.White.copy(alpha = 0.4f)
                ),
                enabled = isReadyToBook,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(if (isReadyToBook) 20.dp else 0.dp, RoundedCornerShape(16.dp), spotColor = Color.White)
            ) {
                Text(
                    text = "CONFIRM BOOKING",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                )
            }
        }
    }
}

// Custom UI Component for Date Cards
@Composable
fun DateCard(date: DateSlot, isSelected: Boolean, onClick: () -> Unit) {
    val bgColor by animateColorAsState(if (isSelected) Color.White else Color(0x1AFFFFFF), label = "")
    val textColor by animateColorAsState(if (isSelected) Color.Black else Color.White, label = "")
    val subTextColor by animateColorAsState(if (isSelected) Color.DarkGray else Color.Gray, label = "")

    Box(
        modifier = Modifier
            .width(70.dp)
            .height(90.dp)
            .background(bgColor, RoundedCornerShape(20.dp))
            .border(1.dp, if (isSelected) Color.Transparent else Color.White.copy(alpha = 0.1f), RoundedCornerShape(20.dp))
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = date.dayOfWeek, style = TextStyle(color = subTextColor, fontSize = 14.sp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = date.dayOfMonth, style = TextStyle(color = textColor, fontSize = 24.sp, fontWeight = FontWeight.Bold))
        }
    }
}

// Custom UI Component for Time and Reason Chips
@Composable
fun TimeChip(time: String, isSelected: Boolean, onClick: () -> Unit) {
    val bgColor by animateColorAsState(if (isSelected) Color.White else Color(0x1AFFFFFF), label = "")
    val textColor by animateColorAsState(if (isSelected) Color.Black else Color.White, label = "")

    Box(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(12.dp))
            .border(1.dp, if (isSelected) Color.Transparent else Color.White.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null, onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = time, style = TextStyle(color = textColor, fontSize = 14.sp, fontWeight = FontWeight.Medium))
    }
}

@Preview(showBackground = true)
@Composable
fun BookSlotScreenPreview() {
    MaterialTheme {
        BookSlotScreen(onBackClick = {}, onConfirmClick = {})
    }
}
