package com.cmpt395.deferralapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cmpt395.deferralapp.state.DeferralAppState
import com.cmpt395.deferralapp.ui.navigation.Screen

@Composable
fun MessagesListScreen(
    appState: DeferralAppState,
    navController: NavHostController
) {
    val threads = appState.chatThreads

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Messages", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(threads) { thread ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { navController.navigate(Screen.Chat.create(thread.id)) }
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(thread.title, style = MaterialTheme.typography.titleMedium)
                        Text("Tap to open chat…")
                    }
                }
            }
        }
    }
}

