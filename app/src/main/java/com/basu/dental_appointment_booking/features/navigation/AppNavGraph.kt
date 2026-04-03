package com.basu.dental_appointment_booking.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.basu.dental_appointment_booking.auth.LoginScreen
import com.basu.dental_appointment_booking.doctor.DoctorLoginScreen
import com.basu.dental_appointment_booking.doctor.DoctorRegistrationScreen
import com.basu.dental_appointment_booking.features.dashboard.DoctorScheduleScreen
import com.basu.dental_appointment_booking.features.doctor.DoctorDashboardScreen
import com.basu.dental_appointment_booking.features.doctor.DoctorProfileScreen
import com.basu.dental_appointment_booking.features.doctor.PatientRecordScreen
import com.basu.dental_appointment_booking.features.user.BookSlotScreen
import com.basu.dental_appointment_booking.features.user.BookingConfirmationScreen
import com.basu.dental_appointment_booking.features.user.BookingHistoryScreen
import com.basu.dental_appointment_booking.features.user.DentistProfileScreen
import com.basu.dental_appointment_booking.features.user.PatientDashboard
import com.basu.dental_appointment_booking.features.user.UserProfileScreen
import com.basu.dental_appointment_booking.features.user.UserRegistration
import com.basu.dental_appointment_booking.intro.NandiOnboardingScreen
import com.basu.dental_appointment_booking.intro.RoleSelectionScreen
import com.basu.dental_appointment_booking.intro.UserRole

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
                    if (role == UserRole.DOCTOR) {
                        navController.navigate(NavRoutes.DoctorLogin.route)
                    } else {
                        navController.navigate(NavRoutes.Login.route)
                    }
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
                },
                onBookingsTabClick = {
                    navController.navigate(NavRoutes.BookingHistory.route)
                },
                onProfileClick = {
                    navController.navigate(NavRoutes.UserProfile.route)
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
        composable(NavRoutes.BookSlot.route) {
            BookSlotScreen(
                onBackClick = { navController.popBackStack() },
                onConfirmClick = {
                    // In the future: Send data to Firebase here.
                    // For the prototype: Navigate back to the Dashboard or to a "Success" screen
                    navController.navigate(NavRoutes.BookingConfirmation.route) {
                        popUpTo(NavRoutes.PatientDashboard.route) { inclusive = true }
                    }
                }
            )
        }
        composable(NavRoutes.BookingHistory.route) {
            BookingHistoryScreen(
                onBackClick = {
                navController.popBackStack()
            },
                onHomeClick = {
                    // Navigate back to Dashboard and clear history from backstack
                    navController.navigate(NavRoutes.PatientDashboard.route) {
                        popUpTo(NavRoutes.PatientDashboard.route) { inclusive = true }
                    }
                },
                onProfileClick = {
                    // If you have a profile route, navigate there
                    navController.navigate(NavRoutes.UserProfile.route)
                }
            )
        }

        composable(NavRoutes.UserProfile.route) {
            UserProfileScreen(
                onHomeClick = {
                    navController.navigate(NavRoutes.PatientDashboard.route) {
                        popUpTo(NavRoutes.PatientDashboard.route) { inclusive = true }
                    }
                },
                onHistoryClick = {
                    navController.navigate(NavRoutes.BookingHistory.route) {
                        popUpTo(NavRoutes.BookingHistory.route) { inclusive = false } // Keeps dashboard alive as root
                    }
                },
                onLogoutClick = {
                    // Log out clears everything and goes to Role Selection or Login
                    navController.navigate(NavRoutes.RoleSelection.route) {
                        popUpTo(NavRoutes.RoleSelection.route) { inclusive = true } // This completely clears the entire backstack!
                    }
                }
            )
        }
        composable(NavRoutes.BookingConfirmation.route) {
            BookingConfirmationScreen(
                onDoneClick = {
                    // Navigate back to Dashboard and clear the booking flow from history
                    navController.navigate(NavRoutes.PatientDashboard.route) {
                        popUpTo(NavRoutes.PatientDashboard.route) { inclusive = true }
                    }
                }
            )
        }
        composable(NavRoutes.DoctorLogin.route) {
            DoctorLoginScreen(
                onBackClick = { navController.popBackStack() },
                onSignUpClick = { navController.navigate(NavRoutes.DoctorRegister.route) },
                onLoginSuccess = {
                    navController.navigate(NavRoutes.DoctorDashboard.route) {
                        popUpTo(NavRoutes.DoctorLogin.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.DoctorRegister.route) {
            DoctorRegistrationScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSignInClick = { // Changed from onRegisterSuccess to onSignInClick
                    navController.navigate(NavRoutes.DoctorLogin.route) {
                        // Clear the registration screen from the backstack
                        popUpTo(NavRoutes.DoctorRegister.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.DoctorDashboard.route) {
            DoctorDashboardScreen(
                onPatientClick = { patientId ->
                    // Navigate to a patient detail screen if you have one
                    // navController.navigate("patient_detail/$patientId")
                },
                onCalendarClick = {
                    // Navigate to Doctor's specific calendar view
                    navController.navigate(NavRoutes.DoctorSchedule.route)
                },
                onRecordsClick = {
                    // Navigate to medical records section
                    navController.navigate(NavRoutes.PatientRecords.route)
                },
                onProfileClick = {
                    navController.navigate(NavRoutes.DoctorProfile.route) {
                        popUpTo(0)
                    }
                }
            )
        }

        composable(NavRoutes.DoctorSchedule.route) {
            DoctorScheduleScreen(
                onHomeClick = {
                    navController.navigate(NavRoutes.DoctorDashboard.route) {
                        popUpTo(NavRoutes.DoctorDashboard.route) { inclusive = true }
                    }
                },
                onRecordsClick = { navController.navigate(NavRoutes.PatientRecords.route)},
                onProfileClick = { navController.navigate(NavRoutes.DoctorProfile.route) }
            )
        }
        composable(NavRoutes.PatientRecords.route) {
            PatientRecordScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(NavRoutes.DoctorProfile.route) {
            DoctorProfileScreen(
                onHomeClick = {
                    navController.navigate(NavRoutes.DoctorDashboard.route) {
                        popUpTo(NavRoutes.DoctorDashboard.route) { inclusive = true }
                    }
                },
                onCalendarClick = {
                    navController.navigate(NavRoutes.DoctorSchedule.route) {
                        popUpTo(NavRoutes.DoctorDashboard.route) { inclusive = false }
                    }
                },
                onRecordsClick = {
                    navController.navigate(NavRoutes.PatientRecords.route) {
                        popUpTo(NavRoutes.DoctorDashboard.route) { inclusive = false }
                    }
                },
                onLogoutClick = {
                    // Logs the doctor out and completely clears the backstack, sending them to Role Selection or Login
                    navController.navigate(NavRoutes.RoleSelection.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}