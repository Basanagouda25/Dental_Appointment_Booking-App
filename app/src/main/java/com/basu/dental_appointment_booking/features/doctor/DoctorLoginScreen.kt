package com.basu.dental_appointment_booking.doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorLoginScreen(
    onBackClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF1E2A38), // Clinical dark blue/grey tint
                        Color.Black
                    ),
                    center = Offset(0.5f, 0.1f),
                    radius = 1500f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 48.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Back Button Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.offset(x = (-12).dp)) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Header
            Text(
                text = "Doctor Portal",
                style = TextStyle(color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.SansSerif)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Sign in to manage your clinic",
                style = TextStyle(color = Color.Gray, fontSize = 16.sp, fontFamily = FontFamily.SansSerif)
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it; errorMessage = null },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Provider Email", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Rounded.Email, contentDescription = "Email", tint = Color.White) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                    focusedContainerColor = Color(0x1AFFFFFF), unfocusedContainerColor = Color(0x1AFFFFFF),
                    focusedBorderColor = if (errorMessage != null) Color.Red else Color(0xFF64B5F6), // Blue focus for doctors
                    unfocusedBorderColor = if (errorMessage != null) Color.Red else Color.White.copy(alpha = 0.3f),
                    cursorColor = Color(0xFF64B5F6)
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it; errorMessage = null },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Password", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = "Lock", tint = Color.White) },
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = "Toggle password", tint = Color.Gray)
                    }
                },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                    focusedContainerColor = Color(0x1AFFFFFF), unfocusedContainerColor = Color(0x1AFFFFFF),
                    focusedBorderColor = if (errorMessage != null) Color.Red else Color(0xFF64B5F6),
                    unfocusedBorderColor = if (errorMessage != null) Color.Red else Color.White.copy(alpha = 0.3f),
                    cursorColor = Color(0xFF64B5F6)
                ),
                shape = RoundedCornerShape(16.dp)
            )

            if (errorMessage != null) {
                Text(text = errorMessage!!, color = Color.Red, fontSize = 12.sp, modifier = Modifier.fillMaxWidth().padding(top = 8.dp, start = 4.dp), textAlign = TextAlign.Start)
            }

            Box(modifier = Modifier.fillMaxWidth().padding(top = 12.dp), contentAlignment = Alignment.CenterEnd) {
                Text(text = "Forgot Password?", style = TextStyle(color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp, fontWeight = FontWeight.Medium), modifier = Modifier.clickable { })
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Main Action Button (Blue hue for Doctors)
            Button(
                onClick = {
                    if (email == "doctor@gmail.com" && password == "admin123") {
                        onLoginSuccess()
                    } else {
                        errorMessage = "Invalid provider credentials"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth().height(60.dp).shadow(20.dp, RoundedCornerShape(16.dp), spotColor = Color(0xFF64B5F6))
            ) {
                Text(text = "CLINIC LOGIN", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text(text = "New practice? ", style = TextStyle(color = Color.Gray, fontSize = 14.sp))
                Text(text = "Register Clinic", style = TextStyle(color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline), modifier = Modifier.clickable { onSignUpClick() })
            }
        }
    }
}