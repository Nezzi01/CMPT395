package com.example.deferalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(chatId: String) {
    var messages by remember {
        mutableStateOf(
            listOf(
                "Hello, how can I help you?",
                "I would like to defer my exam.",
                "Please submit a deferral request in the app."
            )
        )
    }
    var input by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(messages.size) { index ->
                Text(messages[index])
                Spacer(Modifier.height(4.dp))
            }
        }
        Row {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message…") }
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                if (input.isNotBlank()) {
                    messages = messages + "You: $input"
                    input = ""
                }
            }) {
                Text("Send")
            }
        }
    }
}
