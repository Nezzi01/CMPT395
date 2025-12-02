package com.cmpt395.deferralapp.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun HomeProffpopup(classname: String, classsection: String, studentname: String, newongoing: String) {
    Box(modifier = Modifier.fillMaxWidth()
        .height(150.dp),
    ){
        Row {
            Column (
                modifier = Modifier.weight(1f)
                    .padding(5.dp)
            ) {
                Text(text = classname)
                Text(text = classsection)
                Text(text = studentname)
                Text(text = newongoing,
                    modifier = Modifier.padding(top = 10.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            Column (
                modifier = Modifier.weight(1f)
            ) {
                //Image(painter = painterResource(id = R.drawable.img), contentDescription = "User Profile Pic")
            }
        }

    }
}

@Composable
fun HomeProct(roomnum: String, date: String, time: String,) {
    Box(modifier = Modifier.fillMaxWidth()
        .height(150.dp),
    ){
        Row {
            Column (
                modifier = Modifier.weight(1f)
                    .padding(5.dp)
            ) {
                Text(text = roomnum)
                Text(text = date)
                Text(text = time)
            }
            Spacer(modifier = Modifier.weight(1f))
            Column (
                modifier = Modifier.weight(1f).padding(top = 60.dp)
            ) {
                Button(onClick = {}) {
                    Text(text = "More Info")
                }
            }
        }

    }
}