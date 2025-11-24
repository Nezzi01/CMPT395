package com.example.firstapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstapp.ClassInfoCard
import com.example.firstapp.ClassListScreen
import com.example.firstapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar(
    content: @Composable (padding: PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text(text = "Home") },
                    selected = false,
                    onClick = {}
                )

                NavigationDrawerItem(
                    label = {Text(text = "Account")},
                    selected = false,
                    onClick = {}
                )

                NavigationDrawerItem(
                    label = {Text(text = "Settings")},
                    selected = false,
                    onClick = {}
                )

                NavigationDrawerItem(
                    label = {Text(text = "Message")},
                    selected = false,
                    onClick = {}
                )

                NavigationDrawerItem(
                    label = {Text(text = "LogOut")},
                    selected = false,
                    onClick = {}
                )
            }
        }
    ) {
        Scaffold(
            topBar ={
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = colorResource(id = R.color.maroon),
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    ),
                    title = {Text(text = "DIFFER")},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch{drawerState.open()}
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) {
            innerPadding ->
            content(innerPadding)
        }

    }
}