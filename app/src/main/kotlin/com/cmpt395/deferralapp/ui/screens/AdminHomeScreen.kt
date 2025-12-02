package com.cmpt395.deferralapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cmpt395.deferralapp.state.DeferralAppState

@Composable
fun AdminHomeScreen(appState: DeferralAppState) {
    val deferrals = appState.allDeferralsForAdmin()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Admin – All Deferrals", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(deferrals) { req ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray
                    )

                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Student: ${req.student.fullName}")
                        Text("Class: ${req.classItem.name}")
                        Text("Reason: ${req.reason}")
                        Text("Status: ${req.status}")
                        Text("Exam Room: ${req.classItem.location}")
                    }
                }
            }
        }
    }
}
