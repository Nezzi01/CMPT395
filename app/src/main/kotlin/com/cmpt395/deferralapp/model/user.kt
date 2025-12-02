package com.cmpt395.deferralapp.model

enum class UserRole { STUDENT, PROCTOR, ADMIN }

data class User(
    val id: String,
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val role: UserRole,
    val major: String? = null,
    val paymentDetails: String? = null
) {
    val fullName: String get() = "$firstName $lastName"
}
