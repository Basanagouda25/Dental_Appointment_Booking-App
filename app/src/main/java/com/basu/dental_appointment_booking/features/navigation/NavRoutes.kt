package com.basu.dental_appointment_booking.features.navigation

sealed class NavRoutes(val route: String) {
    object getStarted : NavRoutes("get_started")
    object RoleSelection : NavRoutes("role_selection")
    object Login : NavRoutes("login_screen")
    object UserRegister : NavRoutes("user_register")
    object PatientDashboard : NavRoutes("patient_dashboard")
    object DentistProfile : NavRoutes("dentist_profile/{id}"){
        fun createRoute(id: String) = "dentist_profile/$id"
    }
    object BookSlot : NavRoutes("book_slot")
    object BookingHistory: NavRoutes("booking_history")
    object UserProfile : NavRoutes("user_profile")
    object BookingConfirmation : NavRoutes("booking_confirmation")
    object DoctorLogin : NavRoutes("doctor_login")
    object DoctorRegister : NavRoutes("doctor_register")
    object DoctorDashboard : NavRoutes("doctor_dashboard")
    object DoctorSchedule : NavRoutes("doctor_schedule")
    object PatientRecords : NavRoutes("patient_records")
    object DoctorProfile : NavRoutes("doctor_profile")

}