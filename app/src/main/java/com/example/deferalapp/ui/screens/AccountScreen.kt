package com.example.deferalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.deferalapp.state.DeferalAppState

@Composable
fun AccountScreen(appState: DeferalAppState) {
    val user = appState.currentUser

    var first by remember { mutableStateOf(user?.firstName ?: "") }
    var last by remember { mutableStateOf(user?.lastName ?: "") }
    var major by remember { mutableStateOf(user?.major ?: "") }
    var username by remember { mutableStateOf(user?.username ?: "") }
    var payment by remember { mutableStateOf(user?.paymentDetails ?: "") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Account", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = first, onValueChange = { first = it }, label = { Text("First name") })
        OutlinedTextField(value = last, onValueChange = { last = it }, label = { Text("Last name") })
        OutlinedTextField(value = major, onValueChange = { major = it }, label = { Text("Major") })
        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        OutlinedTextField(value = payment, onValueChange = { payment = it }, label = { Text("Payment details") })
        Spacer(Modifier.height(8.dp))
        Button(onClick = { /* pretend to save */ }) {
            Text("Save")
        }
    }
}
