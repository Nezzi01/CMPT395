package com.example.blank

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BookingTimeSlot(roomnum: String, time: String, date: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ){
        Row (modifier = Modifier.fillMaxWidth()){
            Column(modifier = Modifier.weight(1f)) {
                Text(text = roomnum)
                Text(text = time)
                Text(text = date)
            }
            Spacer(modifier = Modifier.weight(1f))
            Column (
                modifier = Modifier.weight(1f).padding(top = 60.dp)
            ) {
                Button(onClick = {}) {
                    Text(text = "Book")
                }
            }
        }
    }
}