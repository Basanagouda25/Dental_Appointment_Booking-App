package com.basu.dental_appointment_booking.features.doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Bloodtype
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Warning
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
data class PastVisit(val date: String, val procedure: String, val toothNotes: String)

val patientHistory = listOf(
    PastVisit("Aug 12, 2026", "Root Canal Treatment", "Upper Right Molar (#3). Successful."),
    PastVisit("Feb 04, 2026", "General Cleaning & X-Ray", "Mild plaque buildup. No cavities spotted."),
    PastVisit("Sep 18, 2025", "Composite Filling", "Lower Left Premolar (#20).")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientRecordScreen(
    patientName: String = "Sarah Jenkins",
    patientId: String = "PT-84729",
    onBackClick: () -> Unit
) {
    var clinicalNotes by remember { mutableStateOf("") }

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
                .padding(bottom = 100.dp) // Space for bottom button
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
                    text = "Medical Record",
                    style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Box(modifier = Modifier.size(48.dp)) // Spacer to balance
            }

            // --- PATIENT PROFILE HEADER ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFFF0F0F0), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Rounded.Person, contentDescription = "Patient", tint = Color.Gray, modifier = Modifier.size(48.dp))
                }

                Spacer(modifier = Modifier.width(20.dp))

                Column {
                    Text(text = patientName, style = TextStyle(color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold))
                    Text(text = "ID: $patientId", style = TextStyle(color = Color.Gray, fontSize = 14.sp, fontFamily = FontFamily.Monospace))
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Rounded.Call, contentDescription = "Phone", tint = Color(0xFF64B5F6), modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "+1 555-0198", style = TextStyle(color = Color(0xFF64B5F6), fontSize = 14.sp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- VITALS & ALERTS ROW ---
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Vitals Box
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0x1AFFFFFF), RoundedCornerShape(16.dp))
                        .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Rounded.Bloodtype, contentDescription = "Blood", tint = Color(0xFFE57373), modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "O+ / Female / 28", style = TextStyle(color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold))
                        }
                    }
                }

                // Alerts Box (Red Tinted)
                Box(
                    modifier = Modifier
                        .weight(1.5f)
                        .background(Color(0x33F44336), RoundedCornerShape(16.dp)) // Translucent Red
                        .border(1.dp, Color(0xFFF44336).copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.Warning, contentDescription = "Alert", tint = Color(0xFFEF5350), modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(text = "Allergies", style = TextStyle(color = Color(0xFFEF5350), fontSize = 10.sp, fontWeight = FontWeight.Bold))
                            Text(text = "Penicillin, Latex", style = TextStyle(color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- TREATMENT HISTORY TIMELINE ---
            Row(modifier = Modifier.padding(horizontal = 24.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.History, contentDescription = "History", tint = Color.White, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Treatment History", style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                patientHistory.forEachIndexed { index, visit ->
                    TimelineItem(visit = visit, isLast = index == patientHistory.size - 1)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- CLINICAL NOTES ---
            Text(
                text = "Add Clinical Note",
                style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = clinicalNotes,
                onValueChange = { clinicalNotes = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 24.dp),
                placeholder = { Text("Enter observations, prescribed meds, or follow-up instructions...", color = Color.Gray, fontSize = 14.sp) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color(0x1AFFFFFF),
                    unfocusedContainerColor = Color(0x1AFFFFFF),
                    focusedBorderColor = Color(0xFF64B5F6),
                    unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
        }

        // --- STICKY BOTTOM BAR (Save Notes) ---
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
                onClick = { /* Save Notes & Go Back */ onBackClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6), contentColor = Color.Black),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(20.dp, RoundedCornerShape(16.dp), spotColor = Color(0xFF64B5F6))
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Save", tint = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "SAVE & CLOSE RECORD",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                )
            }
        }
    }
}

// Sub-component to build a beautiful vertical timeline
@Composable
fun TimelineItem(visit: PastVisit, isLast: Boolean) {
    Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
        // Vertical Line & Dot Indicator
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(20.dp)) {
            Box(modifier = Modifier.size(12.dp).background(Color(0xFF64B5F6), CircleShape))
            if (!isLast) {
                Box(modifier = Modifier.width(2.dp).fillMaxHeight().background(Color.White.copy(alpha = 0.2f)))
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Content Card
        Column(modifier = Modifier.padding(bottom = if (isLast) 0.dp else 24.dp)) {
            Text(text = visit.date, style = TextStyle(color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x1AFFFFFF), RoundedCornerShape(12.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text(text = visit.procedure, style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = visit.toothNotes, style = TextStyle(color = Color.Gray, fontSize = 14.sp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PatientRecordPreview() {
    MaterialTheme {
        PatientRecordScreen(onBackClick = {})
    }
}