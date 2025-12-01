package com.example.deferalapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deferalapp.model.UserRole
import com.example.deferalapp.state.DeferalAppState
import com.example.deferalapp.ui.components.AppTopBar
import com.example.deferalapp.ui.navigation.Screen
import com.example.deferalapp.ui.screens.*
import com.example.deferalapp.ui.theme.DeferalTheme

@Composable
fun AppRoot() {
    val appState = remember { DeferalAppState() }
    val navController = rememberNavController()

    DeferalTheme {

        NavHost(
            navController = navController,
            startDestination = Screen.RoleSelect.route
        ) {

            /* ------------------------------------------------------------ */
            /*                      ROLE SELECT SCREEN                     */
            /* ------------------------------------------------------------ */
            composable(Screen.RoleSelect.route) {
                RoleSelectScreen(
                    onRoleSelected = { role ->
                        navController.navigate(Screen.Login.create(role.name.lowercase()))
                    }
                )
            }

            /* ------------------------------------------------------------ */
            /*                         LOGIN SCREEN                         */
            /* ------------------------------------------------------------ */
            composable(Screen.Login.route) { backStack ->
                val roleName = backStack.arguments?.getString("role") ?: "student"
                val role = UserRole.valueOf(roleName.uppercase())

                LoginScreen(
                    role = role,
                    appState = appState,
                    onLoginSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.RoleSelect.route) { inclusive = true }
                        }
                    }
                )
            }

            /* ------------------------------------------------------------ */
            /*           AUTHENTICATED AREA WITH AppTopBar + Drawer        */
            /* ------------------------------------------------------------ */

            composable(Screen.Home.route) {
                AppTopBar(
                    title = "Differ",
                    onMenuItemClick = { item ->
                        when (item) {
                            "Home" -> navController.navigate(Screen.Home.route)
                            "Messages" -> navController.navigate(Screen.Messages.route)
                            "Account" -> navController.navigate(Screen.Account.route)
                            "Settings" -> navController.navigate(Screen.Settings.route)
                            "Logout" -> {
                                appState.logout()
                                navController.navigate(Screen.RoleSelect.route) {
                                    popUpTo(0)
                                }
                            }
                        }
                    }
                ) {
                    HomeRouterScreen(appState, navController)
                }
            }

            composable(Screen.Messages.route) {
                AppTopBar(
                    title = "Messages",
                    onMenuItemClick = { item ->
                        when (item) {
                            "Home" -> navController.navigate(Screen.Home.route)
                            "Messages" -> {}
                            "Account" -> navController.navigate(Screen.Account.route)
                            "Settings" -> navController.navigate(Screen.Settings.route)
                            "Logout" -> {
                                appState.logout()
                                navController.navigate(Screen.RoleSelect.route) {
                                    popUpTo(0)
                                }
                            }
                        }
                    }
                ) {
                    MessagesListScreen(appState, navController)
                }
            }

            composable(Screen.Account.route) {
                AppTopBar(
                    title = "Account",
                    onMenuItemClick = { item ->
                        when (item) {
                            "Home" -> navController.navigate(Screen.Home.route)
                            "Messages" -> navController.navigate(Screen.Messages.route)
                            "Account" -> {}
                            "Settings" -> navController.navigate(Screen.Settings.route)
                            "Logout" -> {
                                appState.logout()
                                navController.navigate(Screen.RoleSelect.route) {
                                    popUpTo(0)
                                }
                            }
                        }
                    }
                ) {
                    AccountScreen(appState)
                }
            }

            composable(Screen.Settings.route) {
                AppTopBar(
                    title = "Settings",
                    onMenuItemClick = { item ->
                        when (item) {
                            "Home" -> navController.navigate(Screen.Home.route)
                            "Messages" -> navController.navigate(Screen.Messages.route)
                            "Account" -> navController.navigate(Screen.Account.route)
                            "Settings" -> {}
                            "Logout" -> {
                                appState.logout()
                                navController.navigate(Screen.RoleSelect.route) {
                                    popUpTo(0)
                                }
                            }
                        }
                    }
                ) {
                    SettingsScreen()
                }
            }

            composable(Screen.Differ.route) { backStack ->
                val classId = backStack.arguments?.getString("classId") ?: ""
                AppTopBar(
                    title = "Differ Request",
                    onMenuItemClick = { item ->
                        when (item) {
                            "Home" -> navController.navigate(Screen.Home.route)
                            "Messages" -> navController.navigate(Screen.Messages.route)
                            "Account" -> navController.navigate(Screen.Account.route)
                            "Settings" -> navController.navigate(Screen.Settings.route)
                            "Logout" -> {
                                appState.logout()
                                navController.navigate(Screen.RoleSelect.route) {
                                    popUpTo(0)
                                }
                            }
                        }
                    }
                ) {
                    DifferScreen(appState, classId)
                }
            }

            composable(Screen.Chat.route) { backStack ->
                val chatId = backStack.arguments?.getString("chatId") ?: ""
                AppTopBar(
                    title = "Chat",
                    onMenuItemClick = { item ->
                        when (item) {
                            "Home" -> navController.navigate(Screen.Home.route)
                            "Messages" -> navController.navigate(Screen.Messages.route)
                            "Account" -> navController.navigate(Screen.Account.route)
                            "Settings" -> navController.navigate(Screen.Settings.route)
                            "Logout" -> {
                                appState.logout()
                                navController.navigate(Screen.RoleSelect.route) {
                                    popUpTo(0)
                                }
                            }
                        }
                    }
                ) {
                    ChatScreen(chatId)
                }
            }
        }
    }
}



