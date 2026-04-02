package com.basu.dental_appointment_booking.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basu.dental_appointment_booking.R

// This creates the "Half Boxes" / Glass cards seen in the background
@Composable
fun DecorativeGlassCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(140.dp, 180.dp)
            .rotate(15f)
            .alpha(0.15f) // Makes it subtle and "see-through"
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White, Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(400f, 400f)
                ),
                shape = RoundedCornerShape(24.dp)
            )
    )
}

@Composable
fun NandiOnboardingScreen(
    onGetStartedClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF1E1E1E), Color(0xFF000000)),
                    center = Offset(0.5f, 0.4f),
                    radius = 1500f
                )
            )
    ) {
        // --- BACKGROUND DECORATIVE BOXES (THE 4 HALF BOXES) ---
        // Top Left
        DecorativeGlassCard(Modifier.align(Alignment.TopStart).offset(x = (-40).dp, y = 150.dp).rotate(-20f))
        // Top Right
        DecorativeGlassCard(Modifier.align(Alignment.TopEnd).offset(x = 60.dp, y = 80.dp).rotate(10f))
        // Middle Left
        DecorativeGlassCard(Modifier.align(Alignment.CenterStart).offset(x = (-70).dp, y = 100.dp).rotate(-10f))
        // Middle Right
        DecorativeGlassCard(Modifier.align(Alignment.CenterEnd).offset(x = 50.dp, y = 200.dp).rotate(25f))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "NANDI",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 2.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "MULTI SPECIALITY DENTAL CLINIC",
                style = TextStyle(
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(60.dp))

            // --- TOOTH IMAGE SECTION ---
            Box(contentAlignment = Alignment.Center) {
                // Glow behind the tooth
                Box(
                    modifier = Modifier
                        .size(250.dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(Color.White.copy(alpha = 0.15f), Color.Transparent)
                            )
                        )
                )

                Image(
                    painter = painterResource(id = R.drawable.tooth_image_2d),
                    contentDescription = "Dental Icon",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.size(320.dp) // Adjusted size to look big
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "TRANSFORM\nYOUR SMILE",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Book appointments, track treatments, and enjoy seamless care.",
                style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(horizontal = 30.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Action Button
            Button(
                onClick = onGetStartedClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(50.dp), // Fully rounded as per image
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .shadow(30.dp, RoundedCornerShape(50.dp), spotColor = Color.White)
            ) {
                Text(
                    text = "GET STARTED",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}