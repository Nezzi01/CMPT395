package com.cmpt395.deferralapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deferalapp.model.ChatMessage
import com.example.deferalapp.model.MessageSender
import com.cmpt395.deferralapp.ui.theme.Maroon
import com.cmpt395.deferralapp.ui.theme.DeferralAppTheme

// Light grey for incoming messages
private val LightGrey = Color(0xFFEFEFEF)

@Composable
fun ChatScreen(chatId: String) {

    var messages by remember {
        mutableStateOf(
            listOf(
                ChatMessage("m1", chatId, "Hi! How can I help you today?", MessageSender.OTHER, "10:00"),
                ChatMessage("m2", chatId, "I need to defer my COMP 101 exam.", MessageSender.ME, "10:02"),
                ChatMessage("m3", chatId, "Sure, please fill out the form.", MessageSender.OTHER, "10:05")
            )
        )
    }

    var input by remember { mutableStateOf("") }

    fun send() {
        if (input.isBlank()) return

        val msg = ChatMessage(
            id = "m${messages.size + 1}",
            threadId = chatId,
            text = input.trim(),
            sender = MessageSender.ME,
            time = "Now"
        )

        messages = messages + msg
        input = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF2D6)) // your cream background
            .padding(10.dp)
    ) {

        // MESSAGE LIST
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(messages, key = { it.id }) { msg ->
                val isMe = msg.sender == MessageSender.ME

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (isMe) Maroon else LightGrey
                        ),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.widthIn(max = 260.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = msg.text,
                                color = if (isMe) Color.White else Color.Black,
                                fontSize = 16.sp
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = msg.time,
                                color = if (isMe) Color.White.copy(0.8f) else Color.DarkGray,
                                fontSize = 11.sp,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = if (isMe) TextAlign.End else TextAlign.Start
                            )
                        }
                    }
                }
            }
        }

        Divider(Modifier.padding(vertical = 8.dp))

        // INPUT FIELD + SEND BUTTON
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = input,
                onValueChange = { input = it },
                placeholder = { Text("Type a message…") },
                modifier = Modifier.weight(1f),
                maxLines = 3,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = { send() })
            )

            Spacer(Modifier.width(10.dp))

            Button(
                onClick = { send() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Maroon,
                    contentColor = Color.White
                ),
                modifier = Modifier.height(50.dp)
            ) {
                Text("Send")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatScreen() {
    DeferralAppTheme {
        ChatScreen(chatId = "t1")
    }
}
