package com.example.firstapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.components.Topbar

data class ClassInfo(
    val proctor: String,
    val differ: String,
    val room: String,
    val time: String,
    val Date: String,
)

@Composable
fun ClassInfoCard(classInfo: ClassInfo, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .height(110.dp)
            .fillMaxWidth()

    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text("Class -", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(2.dp))
                Text("Proctor: ${classInfo.proctor}")
                Text("Differ: ${classInfo.differ}")
                Text("Room: ${classInfo.room}")
                Text("Time/Date: ${classInfo.time}/ ${classInfo.Date}")
            }

            Button(onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.maroon)
                )) {
                Text(text = "Differ",
                    fontSize = 15.sp)
            }

        }

    }

    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun ClassListScreen(modifier: Modifier) {
    val classList = listOf(
        ClassInfo("Prof. Smith", "MATH 101",  "Room 201", "10:00 AM", "Dec 10, 2025"),
        ClassInfo("Dr. Brown", "CMPT 105", "Room 305", "2:00 PM","Dec 13, 2025")

    )
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 16.dp)
    ){

        items(classList) { classInfo ->
            ClassInfoCard(classInfo = classInfo)
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun prevClassList() {
    Topbar { ClassListScreen(modifier = Modifier) }
}