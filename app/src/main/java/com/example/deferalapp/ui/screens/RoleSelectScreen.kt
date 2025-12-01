package com.example.deferalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deferalapp.model.UserRole
import com.example.deferalapp.ui.theme.DeferalTheme
import com.example.deferalapp.ui.theme.Maroon

@Composable
fun RoleSelectScreen(
    onRoleSelected: (UserRole) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // TITLE
        Text(
            text = "Select Your Role",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(36.dp))

        // STUDENT BUTTON
        Button(
            onClick = { onRoleSelected(UserRole.STUDENT) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Maroon,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Student", style = MaterialTheme.typography.titleMedium.copy(fontSize = 21.sp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        // PROCTOR BUTTON
        Button(
            onClick = { onRoleSelected(UserRole.PROCTOR) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Maroon,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Proctor", style = MaterialTheme.typography.titleMedium.copy(fontSize = 21.sp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ADMIN BUTTON
        Button(
            onClick = { onRoleSelected(UserRole.ADMIN) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Maroon,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Admin / Faculty", style = MaterialTheme.typography.titleMedium.copy(fontSize = 21.sp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoleSelectScreen() {
    DeferalTheme {
        RoleSelectScreen(
            onRoleSelected = {}
        )
    }
}

