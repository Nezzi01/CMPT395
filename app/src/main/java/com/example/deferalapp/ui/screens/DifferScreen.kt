package com.example.deferalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.deferalapp.state.DeferalAppState

@Composable
fun DifferScreen(
    appState: DeferalAppState,
    classId: String
) {
    val user = appState.currentUser
    val cls = appState.getClassById(classId)
    var reason by remember { mutableStateOf("") }
    var status by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Deferral Request", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = user?.fullName ?: "",
            onValueChange = {},
            label = { Text("Student") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            value = cls?.name ?: "",
            onValueChange = {},
            label = { Text("Class") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            value = "${cls?.finalDate} • ${cls?.finalTime}",
            onValueChange = {},
            label = { Text("Exam Time") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = reason,
            onValueChange = { reason = it },
            label = { Text("Reason for deferral") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(onClick = {
                if (reason.isNotBlank() && cls != null) {
                    appState.createDeferral(classId, reason)
                    status = "Request sent to proctor and faculty (simulated)."
                } else {
                    status = "Please enter a reason."
                }
            }) {
                Text("Send")
            }
        }
        status?.let {
            Spacer(Modifier.height(8.dp))
            Text(it)
        }
    }
}

