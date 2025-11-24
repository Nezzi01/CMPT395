package com.example.blank

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeferralRequest (firstname: String, lastname: String, email: String, address: String, cityprovince: String, postalcode: String, studentid: String) {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(top = 50.dp)
                .background(color = Color.LightGray)
        ) {
            Column()
            {
                Row(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .fillMaxWidth(),
                    Arrangement.Center
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = firstname,
                        onValueChange = {},
                        label = { Text("First Name") },
                        modifier = Modifier
                            .width(175.dp)
                            .padding(end = 10.dp)
                    )
                    OutlinedTextField(
                        readOnly = true,
                        value = lastname,
                        onValueChange = {},
                        label = { Text("Last Name") },
                        modifier = Modifier.width(175.dp),
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .fillMaxWidth(),
                    Arrangement.Center
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = studentid,
                        onValueChange = {},
                        label = { Text("Student Id") },
                        modifier = Modifier
                            .width(175.dp)
                            .padding(end = 10.dp)
                    )
                    OutlinedTextField(
                        readOnly = true,
                        value = address,
                        onValueChange = {},
                        label = { Text("Address") },
                        modifier = Modifier.width(175.dp),
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .fillMaxWidth(),
                    Arrangement.Center
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = cityprovince,
                        onValueChange = {},
                        label = { Text("City/Province") },
                        modifier = Modifier
                            .width(175.dp)
                            .padding(end = 10.dp)
                    )
                    OutlinedTextField(
                        readOnly = true,
                        value = postalcode,
                        onValueChange = {},
                        label = { Text("Postal Code") },
                        modifier = Modifier.width(175.dp),
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .fillMaxWidth(),
                    Arrangement.Center
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = email,
                        onValueChange = {},
                        label = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp)
                            .padding(start = 10.dp),
                    )
                }


                Text(
                    text = "Please enter your reason for your deferral request below: (512 Character limit)",
                    fontSize = 12.sp
                )

            }

        }
        Box(){
            var deferralinput by remember{
                mutableStateOf("")
            }
            TextField(
                value = deferralinput,
                onValueChange = { text: String ->
                    if (deferralinput.length <= 511) deferralinput = text
                },
                modifier = Modifier
                    .fillMaxWidth()


            )
        }
        Spacer(Modifier.weight(1f))
        Button(onClick = {}, Modifier) {
            Text(text = "Submit")
        }
    }

}
