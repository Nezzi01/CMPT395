package com.example.deferalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.deferalapp.state.DeferalAppState
import com.example.deferalapp.ui.navigation.Screen
import com.example.deferalapp.ui.theme.CardGrey

@Composable
fun StudentHomeScreen(
    appState: DeferalAppState,
    navController: NavHostController
) {
    val classes = appState.getStudentClasses()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Your Classes", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(classes) { cls ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = CardGrey)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(cls.name, style = MaterialTheme.typography.titleMedium)
                            Text("Proctor: ${cls.proctorName}", color = MaterialTheme.colorScheme.onSurface)
                            Text("${cls.finalDate} • ${cls.finalTime}")
                        }
                        Spacer(Modifier.width(8.dp))
                        Button(
                            onClick = { navController.navigate(Screen.Differ.create(cls.id)) },
                            modifier = Modifier.alignByBaseline()
                        ) {
                            Text("Differ")
                        }
                    }
                }
            }
        }
    }
}

