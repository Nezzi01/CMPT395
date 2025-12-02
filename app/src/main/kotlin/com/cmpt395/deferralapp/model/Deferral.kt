package com.cmpt395.deferralapp.model

enum class DeferralStatus { PENDING, APPROVED, REJECTED }

data class DeferralRequest(
    val id: String,
    val student: User,
    val classItem: ClassItem,
    val reason: String,
    val status: DeferralStatus = DeferralStatus.PENDING
)
