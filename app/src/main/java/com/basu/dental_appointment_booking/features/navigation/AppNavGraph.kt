package com.basu.dental_appointment_booking.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost // Ensure this is the correct import
import androidx.navigation.compose.composable
import com.basu.dental_appointment_booking.auth.LoginScreen
import com.basu.dental_appointment_booking.features.user.UserRegistration
import com.basu.dental_appointment_booking.intro.NandiOnboardingScreen
import com.basu.dental_appointment_booking.intro.RoleSelectionScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    // The NavHost composable requires the navController and startDestination
    NavHost(
        navController = navController,
        startDestination = NavRoutes.getStarted.route
    ) {
        // Route: Get Started
        composable(NavRoutes.getStarted.route) {
            NandiOnboardingScreen(
                onGetStartedClick = {
                    navController.navigate(NavRoutes.RoleSelection.route)
                }
            )
        }

        // Route: Role Selection
        composable(NavRoutes.RoleSelection.route) {
            RoleSelectionScreen(
                onBackClick = { navController.popBackStack() },
                onContinueClick = { role ->
                    // Pass the role as a argument if needed, or just navigate to login
                    navController.navigate(NavRoutes.Login.route)
                }
            )
        }

        composable(NavRoutes.Login.route) {
            LoginScreen(
                onBackClick = { navController.popBackStack() },
                onSignUpClick = {
                    // Navigate to SignUp when you create it
                    navController.navigate(NavRoutes.UserRegister.route)
                }
            )
        }

        composable(NavRoutes.UserRegister.route) {
            UserRegistration(
                onBackClick = { navController.popBackStack() },
                onSignInClick = {
                    // After successful registration, go back to Login
                    navController.navigate(NavRoutes.Login.route) {
                        // Clear backstack so user doesn't go back to registration page
                        popUpTo(NavRoutes.UserRegister.route) { inclusive = true }
                    }
                }
            )
        }
    }
}