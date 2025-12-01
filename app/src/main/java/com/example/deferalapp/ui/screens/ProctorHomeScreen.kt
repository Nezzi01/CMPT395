package com.example.deferalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.deferalapp.model.DeferralStatus
import com.example.deferalapp.state.DeferalAppState

@Composable
fun ProctorHomeScreen(appState: DeferalAppState) {
    val deferrals = appState.deferralsForProctor()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Deferral Requests", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(deferrals) { req ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(req.student.fullName, style = MaterialTheme.typography.titleMedium)
                        Text("Class: ${req.classItem.name}")
                        Text("Reason: ${req.reason}")
                        Text("Status: ${req.status}")
                        Spacer(Modifier.height(8.dp))
                        Row {
                            Button(onClick = { appState.updateDeferralStatus(req.id, DeferralStatus.APPROVED) }) {
                                Text("Accept")
                            }
                            Spacer(Modifier.width(8.dp))
                            Button(onClick = { appState.updateDeferralStatus(req.id, DeferralStatus.REJECTED) }) {
                                Text("Reject")
                            }
                        }
                    }
                }
            }
        }
    }
}

