package com.cmpt395.deferralapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RoomInfoPage(roomnumber: String, roompersonlimit: String, date: String, time: String, assignedproctor: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(modifier = Modifier.fillMaxSize()
            .padding(top = 50.dp)
        ){
            Box(Modifier
                .background(color = Color.LightGray)
                .fillMaxWidth()
            )
            {
                Text(text = "Room Info:", modifier = Modifier.padding(bottom = 20.dp))
            }
            Text(text = roomnumber)
            Text(text = roompersonlimit)
            Text(text = date)
            Text(text = time)
            Text(text = assignedproctor)

        }
    }
}