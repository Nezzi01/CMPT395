package com.example.deferalapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.deferalapp.model.UserRole
import com.example.deferalapp.state.DeferalAppState

@Composable
fun HomeRouterScreen(
    appState: DeferalAppState,
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
