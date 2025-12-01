package com.example.deferalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    var darkMode by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Settings")
        Spacer(Modifier.height(8.dp))
        Row {
            Text("Dark mode")
            Spacer(Modifier.width(8.dp))
            Switch(checked = darkMode, onCheckedChange = { darkMode = it })
        }
        Spacer(Modifier.height(8.dp))
        Text("Other settings can go here.")
    }
}
