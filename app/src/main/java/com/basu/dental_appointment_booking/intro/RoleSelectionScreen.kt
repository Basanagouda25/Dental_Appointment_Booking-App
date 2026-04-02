package com.basu.dental_appointment_booking.intro

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MedicalServices
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
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
fun BackgroundGlassOverlay(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(300.dp, 400.dp)
            .shadow(16.dp, RoundedCornerShape(24.dp), clip = false)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0x26FFFFFF),
                        Color(0x0DFFFFFF)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(200f, 300f)
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .alpha(0.5f)
            .blur(12.dp)
            .clip(RoundedCornerShape(24.dp))
    )
}

// FIXED: Removed navigation parameters from the individual card
@Composable
fun RoleSelectionCard(
    title: String,
    description: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else Color(0x1AFFFFFF),
        animationSpec = tween(300), label = "bgColor"
    )
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) Color.Black else Color.White,
        animationSpec = tween(300), label = "contentColor"
    )
    val glowElevation by animateDpAsState(
        targetValue = if (isSelected) 20.dp else 0.dp,
        animationSpec = tween(300), label = "glow"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .shadow(
                elevation = glowElevation,
                shape = RoundedCornerShape(20.dp),
                clip = false,
                ambientColor = Color.White.copy(alpha = 0.5f),
                spotColor = Color.White
            )
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = if (isSelected) Color.Transparent else Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = if (isSelected) Color(0xFFF0F0F0) else Color.White.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = contentColor,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column {
                Text(
                    text = title,
                    style = TextStyle(
                        color = contentColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 1.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = TextStyle(
                        color = contentColor.copy(alpha = if (isSelected) 0.7f else 0.5f),
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            }
        }
    }
}

enum class UserRole {
    PATIENT, DOCTOR
}

// FIXED: Added navigation parameters to the main Screen composable
@Composable
fun RoleSelectionScreen(
    onBackClick: () -> Unit,
    onContinueClick: (UserRole) -> Unit
) {
    var selectedRole by remember { mutableStateOf<UserRole?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF222222),
                        Color.Black
                    ),
                    center = Offset(0.5f, -0.2f),
                    radius = 1500f
                )
            )
    ) {
        BackgroundGlassOverlay(modifier = Modifier.align(Alignment.TopEnd).offset(x = 100.dp, y = -50.dp).rotate(25f))
        BackgroundGlassOverlay(modifier = Modifier.align(Alignment.BottomStart).offset(x = -120.dp, y = 100.dp).rotate(-15f))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- NEW: Back Button Row ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.offset(x = (-12).dp)) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Navigate Back",
                        tint = Color.White
                    )
                }
            }

            // Header Section
            Text(
                text = "NANDI",
                style = TextStyle(
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Choose Your Path",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.SansSerif
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Select your role to configure your dashboard.",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Patient Card
            RoleSelectionCard(
                title = "Patient",
                description = "Book visits & track treatments",
                icon = Icons.Rounded.Person,
                isSelected = selectedRole == UserRole.PATIENT,
                onClick = { selectedRole = UserRole.PATIENT }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Doctor Card
            RoleSelectionCard(
                title = "Doctor",
                description = "Manage schedules & records",
                icon = Icons.Rounded.MedicalServices,
                isSelected = selectedRole == UserRole.DOCTOR,
                onClick = { selectedRole = UserRole.DOCTOR }
            )

            Spacer(modifier = Modifier.weight(1f))

            // --- FIXED: Wired up the Continue Button ---
            Button(
                onClick = {
                    selectedRole?.let { role ->
                        onContinueClick(role)
                    }
                },
                enabled = selectedRole != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.White.copy(alpha = 0.2f),
                    disabledContentColor = Color.White.copy(alpha = 0.4f)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(
                        elevation = if (selectedRole != null) 20.dp else 0.dp,
                        shape = RoundedCornerShape(16.dp),
                        ambientColor = Color.White,
                        spotColor = Color.White
                    )
            ) {
                Text(
                    text = "CONTINUE",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoleSelectionPreview() {
    MaterialTheme {
        // Provide dummy functions for the preview to compile
        RoleSelectionScreen(
            onBackClick = {},
            onContinueClick = {}
        )
    }
}