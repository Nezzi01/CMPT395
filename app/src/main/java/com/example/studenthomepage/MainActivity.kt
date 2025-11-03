package com.example.studenthomepage

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studenthomepage.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentHomePageTheme {
                AppNavigation()
            }
        }
    }
}

// --- Navigation ---
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { StudentHomePage(navController) }
        composable("account") { AccountDetailsScreen(navController) }
    }
}

// --- Home Screen ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentHomePage(navController: NavHostController) {
    var menuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .padding(16.dp)
    ) {
        // Top Bar
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "GRRV",
                    color = TextWhite,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = { menuExpanded = !menuExpanded }) {
                    Icon(
                        imageVector = if (menuExpanded) Icons.Filled.Close else Icons.Filled.Menu,
                        contentDescription = "Menu",
                        tint = TextWhite
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = GRRVRed
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown Menu
        if (menuExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardWhite, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MenuButton("Home") { navController.navigate("home") }
                MenuButton("Account") { navController.navigate("account") }
                MenuButton("Settings") { /* TODO */ }
                MenuButton("Messages") { /* TODO */ }
                MenuButton("Log Out") { /* TODO */ }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Student Cards
        repeat(3) {
            StudentCard()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun MenuButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = CardWhite)
    ) {
        Text(text = text, color = TextBlack)
    }
}

@Composable
fun StudentCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardWhite, RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Class: CMPT 395", color = TextBlack, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text("Room: 6-153", color = TextGray, fontSize = 14.sp)
            Text("Time/Date: 10:00 AM, Nov 2", color = TextGray, fontSize = 14.sp)
        }

        Button(
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(containerColor = GRRVRed)
        ) {
            Text("Differ It", color = TextWhite)
        }
    }
}

// --- Account Details Screen ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailsScreen(navController: NavHostController) {
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
                        color = TextWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextWhite)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = GRRVRed)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(BackgroundLight)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val fieldModifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)

            OutlinedTextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name") }, modifier = fieldModifier)
            OutlinedTextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") }, modifier = fieldModifier)
            OutlinedTextField(value = studentId, onValueChange = { studentId = it }, label = { Text("Student ID") }, modifier = fieldModifier)
            OutlinedTextField(value = macEwanEmail, onValueChange = { macEwanEmail = it }, label = { Text("MacEwan Email") }, modifier = fieldModifier)
            OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") }, modifier = fieldModifier)
            OutlinedTextField(value = cityProvince, onValueChange = { cityProvince = it }, label = { Text("City / Province") }, modifier = fieldModifier)
            OutlinedTextField(value = postalCode, onValueChange = { postalCode = it }, label = { Text("Postal Code") }, modifier = fieldModifier)

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    saveUserDetails(
                        firstName,
                        lastName,
                        studentId,
                        macEwanEmail,
                        address,
                        cityProvince,
                        postalCode
                    )
                    navController.navigate("home") {
                        popUpTo("account") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = GRRVRed),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save", color = TextWhite, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// --- Save function ---
fun saveUserDetails(
    firstName: String,
    lastName: String,
    studentId: String,
    macEwanEmail: String,
    address: String,
    cityProvince: String,
    postalCode: String
) {
    Log.d(
        "AccountDetails",
        "Saved: $firstName, $lastName, $studentId, $macEwanEmail, $address, $cityProvince, $postalCode"
    )
}
