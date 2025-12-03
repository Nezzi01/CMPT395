package com.example.deferalapp.model

// Each conversation in the Messages list
data class ChatThread(
    val id: String,
    val title: String // e.g. "Prof Smith"
)

// Who sent the message
enum class MessageSender {
    ME,
    OTHER
}

// Single chat bubble
data class ChatMessage(
    val id: String,
    val threadId: String,
    val text: String,
    val sender: MessageSender,
    val time: String
)