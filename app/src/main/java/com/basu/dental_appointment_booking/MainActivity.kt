package com.basu.dental_appointment_booking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.basu.dental_appointment_booking.intro.NandiOnboardingScreen
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.basu.dental_appointment_booking.features.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNavGraph(navController = navController)
        }
    }
}