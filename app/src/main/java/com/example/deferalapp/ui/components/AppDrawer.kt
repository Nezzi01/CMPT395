package com.example.deferalapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppDrawer(
    onHome: () -> Unit,
    onMessages: () -> Unit,
    onAccount: () -> Unit,
    onSettings: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF2D6))  // cream background
            .padding(top = 0.dp)            // start BELOW top bar
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        DrawerItem("Home", onHome)
        DrawerDivider()

        DrawerItem("Messages", onMessages)
        DrawerDivider()

        DrawerItem("Account", onAccount)
        DrawerDivider()

        DrawerItem("Settings", onSettings)

        Spacer(modifier = Modifier.weight(1f))

        DrawerDivider()
        DrawerItem("Logout", onLogout)
    }
}

@Composable
private fun DrawerItem(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 18.dp, horizontal = 20.dp)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}

@Composable
private fun DrawerDivider() {
    Divider(
        thickness = 1.dp,
        color = Color.Black.copy(alpha = 0.4f)
    )
}

