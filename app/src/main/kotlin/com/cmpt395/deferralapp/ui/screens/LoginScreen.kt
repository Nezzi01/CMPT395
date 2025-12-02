package com.cmpt395.deferralapp.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmpt395.deferralapp.model.UserRole
import com.cmpt395.deferralapp.state.DeferralAppState
import com.cmpt395.deferralapp.ui.theme.DeferralAppTheme
import com.cmpt395.deferralapp.ui.theme.Maroon

@Composable
fun LoginScreen(
    role: UserRole,
    onLoginSuccess: () -> Unit,
    appState: DeferralAppState
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TITLE
        Text(
            "Sign in as ${role.name.lowercase().replaceFirstChar { it.uppercase() }}",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(28.dp))

        // USERNAME FIELD
        ThickOutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                showError = false
            },
            label = "Username",
            isError = showError
        )

        Spacer(Modifier.height(16.dp))

        // PASSWORD FIELD
        ThickPasswordField(
            value = password,
            onValueChange = {
                password = it
                showError = false
            },
            label = "Password",
            isError = showError,
            passwordVisible = passwordVisible,
            onVisibilityChange = { passwordVisible = !passwordVisible }
        )

        if (showError) {
            Spacer(Modifier.height(8.dp))
            Text(
                "Invalid username or password",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(Modifier.height(32.dp))

        // SIGN IN BUTTON
        Button(
            onClick = {
                val ok = appState.login(role, username.trim(), password)
                showError = !ok
                if (ok) onLoginSuccess()
            },
            modifier = Modifier
                .width(150.dp)
                .height(60.dp)
        ) {
            Text(
                "Sign In",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun ThickOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean
) {
    // Draw your own 2dp border (the real one)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = if (isError) MaterialTheme.colorScheme.error else Maroon,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(1.dp) // ensures spacing looks correct
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent
            )
        )
    }
}

@Composable
fun ThickPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    passwordVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    // SAME BORDER as username
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = if (isError) MaterialTheme.colorScheme.error else Maroon,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(1.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            visualTransformation =
                if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = onVisibilityChange) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Default.Visibility
                        else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    DeferralAppTheme {
        LoginScreen(
            role = UserRole.STUDENT,
            onLoginSuccess = {},
            appState = DeferralAppState()
        )
    }
}


