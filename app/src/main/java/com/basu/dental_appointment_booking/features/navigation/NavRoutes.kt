package com.basu.dental_appointment_booking.features.navigation

sealed class NavRoutes(val route: String) {
    object getStarted : NavRoutes("get_started")
    object RoleSelection : NavRoutes("role_selection")
    object Login : NavRoutes("login_screen")
    object UserRegister : NavRoutes("user_register")
}