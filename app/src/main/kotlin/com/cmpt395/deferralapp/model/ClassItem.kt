package com.cmpt395.deferralapp.model

data class ClassItem(
    val id: String,
    val name: String,
    val proctorId: String,
    val proctorName: String,
    val finalDate: String,
    val finalTime: String,
    val location: String
)
