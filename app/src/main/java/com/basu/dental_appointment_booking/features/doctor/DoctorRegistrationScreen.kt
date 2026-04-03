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
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocalHospital
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorRegistrationScreen(
    onBackClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var clinicName by remember { mutableStateOf("") }
    var licenseNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF1E2A38), Color.Black),
                    center = Offset(0.5f, 0.1f), radius = 1500f
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
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackClick, modifier = Modifier.offset(x = (-12).dp)) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Register Practice", style = TextStyle(color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.SansSerif))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Set up your clinical dashboard", style = TextStyle(color = Color.Gray, fontSize = 16.sp, fontFamily = FontFamily.SansSerif))

            Spacer(modifier = Modifier.height(40.dp))

            // Dr. Name
            OutlinedTextField(
                value = name, onValueChange = { name = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Full Name (e.g. Dr. Jane Doe)", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Rounded.Person, contentDescription = "Name", tint = Color.White) }, singleLine = true,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedContainerColor = Color(0x1AFFFFFF), unfocusedContainerColor = Color(0x1AFFFFFF), focusedBorderColor = Color(0xFF64B5F6), unfocusedBorderColor = Color.White.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Clinic Name
            OutlinedTextField(
                value = clinicName, onValueChange = { clinicName = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Clinic / Practice Name", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Rounded.LocalHospital, contentDescription = "Clinic", tint = Color.White) }, singleLine = true,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedContainerColor = Color(0x1AFFFFFF), unfocusedContainerColor = Color(0x1AFFFFFF), focusedBorderColor = Color(0xFF64B5F6), unfocusedBorderColor = Color.White.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // License Number
            OutlinedTextField(
                value = licenseNumber, onValueChange = { licenseNumber = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Medical License No.", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Rounded.Badge, contentDescription = "License", tint = Color.White) }, singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedContainerColor = Color(0x1AFFFFFF), unfocusedContainerColor = Color(0x1AFFFFFF), focusedBorderColor = Color(0xFF64B5F6), unfocusedBorderColor = Color.White.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Email
            OutlinedTextField(
                value = email, onValueChange = { email = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Business Email", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Rounded.Email, contentDescription = "Email", tint = Color.White) }, singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedContainerColor = Color(0x1AFFFFFF), unfocusedContainerColor = Color(0x1AFFFFFF), focusedBorderColor = Color(0xFF64B5F6), unfocusedBorderColor = Color.White.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Password
            OutlinedTextField(
                value = password, onValueChange = { password = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Password", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = "Lock", tint = Color.White) },
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible })
                    {
                        Icon(imageVector = image, contentDescription = "Toggle password", tint = Color.Gray) }
                }, singleLine = true, visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedContainerColor = Color(0x1AFFFFFF), unfocusedContainerColor = Color(0x1AFFFFFF), focusedBorderColor = Color(0xFF64B5F6), unfocusedBorderColor = Color.White.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Action Button
            Button(
                onClick = { /* Handle Registration */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black), shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth().height(60.dp).shadow(20.dp, RoundedCornerShape(16.dp), spotColor = Color(0xFF64B5F6))
            ) {
                Text(text = "CREATE ACCOUNT", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Already registered? ", style = TextStyle(color = Color.Gray, fontSize = 14.sp))
                Text(text = "Clinic Login", style = TextStyle(color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline), modifier = Modifier.clickable { onSignInClick() })
            }
        }
    }
}