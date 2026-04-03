package com.basu.dental_appointment_booking.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost // Ensure this is the correct import
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.basu.dental_appointment_booking.auth.LoginScreen
import com.basu.dental_appointment_booking.features.user.DentistProfileScreen
import com.basu.dental_appointment_booking.features.user.PatientDashboard
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
                onSignUpClick = { navController.navigate(NavRoutes.UserRegister.route) },
                onLoginSuccess = {
                    // Navigate to Dashboard and clear the backstack so they can't hit 'back' to return to Login
                    navController.navigate(NavRoutes.PatientDashboard.route) {
                        popUpTo(NavRoutes.Login.route) { inclusive = true }
                    }
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
        composable(NavRoutes.PatientDashboard.route) {
            PatientDashboard(
                // Use 'onBookClick' here to match the parameter in Dashboard.kt
                onBookClick = { id ->
                    navController.navigate(NavRoutes.DentistProfile.createRoute(id))
                }
            )
        }

        composable(
            route = NavRoutes.DentistProfile.route,
        ) { backStackEntry ->

            // NOTE: In the final app with Firebase, you would extract the clicked
            // dentist's ID or name from the backStackEntry arguments right here.
            // For this frontend prototype, we will use our premium placeholder data.

            DentistProfileScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                dentistName = "Dr. Emily Chen",
                specialty = "Orthodontist",
                onBookSlotClick = {
                    // Navigates to the upcoming Calendar / Time Slot screen
                    // Make sure NavRoutes.BookSlot is defined in your NavRoutes sealed class!
                    navController.navigate(NavRoutes.BookSlot.route)
                }
            )
        }
    }
}