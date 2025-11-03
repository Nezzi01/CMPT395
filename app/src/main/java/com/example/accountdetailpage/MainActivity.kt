package com.example.accountdetailpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.accountdetailpage.ui.theme.AccountDetailPageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountDetailPageTheme {
                AccountDetailsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailsScreen() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }
    var macEwanEmail by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var cityProvince by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ACCOUNT DETAILS",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val fieldModifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)

            OutlinedTextField(
                value = firstName, onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = fieldModifier,
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = lastName, onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = fieldModifier,
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = studentId, onValueChange = { studentId = it },
                label = { Text("Student ID") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = fieldModifier,
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = macEwanEmail, onValueChange = { macEwanEmail = it },
                label = { Text("MacEwan Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = fieldModifier,
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = address, onValueChange = { address = it },
                label = { Text("Address") },
                modifier = fieldModifier,
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = cityProvince, onValueChange = { cityProvince = it },
                label = { Text("City / Province") },
                modifier = fieldModifier,
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = postalCode, onValueChange = { postalCode = it },
                label = { Text("Postal Code") },
                modifier = fieldModifier,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* Save later */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Save",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
