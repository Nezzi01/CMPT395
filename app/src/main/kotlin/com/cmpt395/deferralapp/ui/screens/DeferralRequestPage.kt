package com.cmpt395.deferralapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmpt395.deferralapp.state.DeferralAppState

@Composable
fun DeferralRequestPage(
    appState: DeferralAppState,
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