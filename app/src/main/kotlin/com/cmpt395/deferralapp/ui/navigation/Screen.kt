package com.cmpt395.deferralapp.ui.navigation

sealed class Screen(val route: String) {
    object RoleSelect : Screen("role_select")
    object Login : Screen("login/{role}") {
        fun create(role: String) = "login/$role"
    }
    object Home : Screen("home")
    object Messages : Screen("messages")
    object Account : Screen("account")
    object Settings : Screen("settings")
    object Differ : Screen("differ/{classId}") {
        fun create(classId: String) = "differ/$classId"
    }
    object Chat : Screen("chat/{chatId}") {
        fun create(id: String) = "chat/$id"
    }
    object Logout : Screen("logout")
}
