package com.cmpt395.deferralapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.cmpt395.deferralapp.model.UserRole
import com.cmpt395.deferralapp.state.DeferralAppState

@Composable
fun HomeRouterScreen(
    appState: DeferralAppState,
    navController: NavHostController
) {
    val user = appState.currentUser
    when (user?.role) {
        UserRole.STUDENT -> StudentHomeScreen(appState, navController)
        UserRole.PROCTOR -> ProctorHomeScreen(appState)
        UserRole.ADMIN -> AdminHomeScreen(appState)
        else -> GuestScreen()
    }
}
