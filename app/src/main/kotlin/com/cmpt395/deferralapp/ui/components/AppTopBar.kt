package com.cmpt395.deferralapp.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmpt395.deferralapp.ui.theme.Maroon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String = "Differ",
    onMenuClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onMenuItemClick: (String) -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    var drawerOpen by remember { mutableStateOf(false) }

    // Background dim animation
    val dimAlpha by animateFloatAsState(if (drawerOpen) 0.4f else 0f)

    Box(modifier = Modifier.fillMaxSize()) {

        Column {

            // TOP BAR — CENTERED TITLE ✔
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { drawerOpen = true }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNotificationsClick) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Maroon
                )
            )

            // SCREEN CONTENT
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                content()
            }
        }

        // DIM BACKGROUND WHEN MENU OPEN ✔
        if (dimAlpha > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(dimAlpha)
                    .background(Color.Black)
                    .clickable { drawerOpen = false }
            )
        }

        // SLIDE-IN DRAWER ✔
        DrawerMenu(
            visible = drawerOpen,
            onClose = { drawerOpen = false },
            onMenuItemClick = {
                drawerOpen = false
                onMenuItemClick(it)
            }
        )
    }
}

/* -------------------------------------------------------------------------- */
/*                                DRAWER MENU                                 */
/* -------------------------------------------------------------------------- */

@Composable
private fun DrawerMenu(
    visible: Boolean,
    onClose: () -> Unit,
    onMenuItemClick: (String) -> Unit
) {
    val offsetX by animateDpAsState(
        targetValue = if (visible) 0.dp else (-260).dp
    )

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(260.dp)
            .offset(x = offsetX)
            .background(Color(0xFFFFF2D6))  // Cream background ✔
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(16.dp))

            DrawerItem("Home", onMenuItemClick)
            DrawerDivider()

            DrawerItem("Messages", onMenuItemClick)
            DrawerDivider()

            DrawerItem("Account", onMenuItemClick)
            DrawerDivider()

            DrawerItem("Settings", onMenuItemClick)

            Spacer(modifier = Modifier.weight(1f))

            DrawerDivider()
            DrawerItem("Logout", onMenuItemClick)
        }
    }
}

@Composable
private fun DrawerItem(
    text: String,
    onClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(text) }
            .padding(vertical = 18.dp, horizontal = 20.dp)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,      // REQUIRED — 20.sp ✔
            color = Color.Black    // black text ✔
        )
    }
}

@Composable
private fun DrawerDivider() {
    Divider(
        thickness = 1.dp,
        color = Color.Black.copy(alpha = 0.4f)   // thin black line ✔
    )
}
