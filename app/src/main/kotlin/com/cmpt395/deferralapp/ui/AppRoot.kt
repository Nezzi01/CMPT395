package com.cmpt395.deferralapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cmpt395.deferralapp.model.UserRole
import com.cmpt395.deferralapp.state.DeferralAppState
import com.cmpt395.deferralapp.state.SettingsManager
import com.cmpt395.deferralapp.ui.components.AppTopBar
import com.cmpt395.deferralapp.ui.navigation.Screen
import com.cmpt395.deferralapp.ui.screens.*
import com.cmpt395.deferralapp.ui.theme.DeferralAppTheme

@Composable
fun AppRoot(
    settingsManager: SettingsManager,
    useDarkMode: Boolean
) {
    val appState = remember { DeferralAppState() }
    val navController = rememberNavController()

    DeferralAppTheme(darkTheme = useDarkMode) {

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

            val sideMenuNavHandler: (String) -> Unit = {
                when (it) {
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

            composable(Screen.Home.route) {
                AppTopBar(
                    title = "Differ",
                    onMenuItemClick = sideMenuNavHandler
                ) {
                    HomeRouterScreen(appState, navController)
                }
            }

            composable(Screen.Messages.route) {
                AppTopBar(
                    title = "Messages",
                    onMenuItemClick = sideMenuNavHandler
                ) {
                    MessagesListScreen(appState, navController)
                }
            }

            composable(Screen.Account.route) {
                AppTopBar(
                    title = "Account",
                    onMenuItemClick = sideMenuNavHandler
                ) {
                    AccountScreen(appState)
                }
            }

            composable(Screen.Settings.route) {
                AppTopBar(
                    title = "Settings",
                    onMenuItemClick = sideMenuNavHandler
                ) {
                    SettingsList(settingsManager)
                }
            }

            composable(Screen.Differ.route) { backStack ->
                val classId = backStack.arguments?.getString("classId") ?: ""
                AppTopBar(
                    title = "Differ Request",
                    onMenuItemClick = sideMenuNavHandler
                ) {
                    DeferralRequestPage(appState, classId)
                }
            }

            composable(Screen.Chat.route) { backStack ->
                val chatId = backStack.arguments?.getString("chatId") ?: ""
                AppTopBar(
                    title = "Chat",
                    onMenuItemClick = sideMenuNavHandler
                ) {
                    ChatScreen(chatId)
                }
            }
        }
    }
}