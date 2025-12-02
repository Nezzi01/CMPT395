package com.cmpt395.deferralapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmpt395.deferralapp.R

@Composable
fun AccountPage(firstname: String, lastname: String, email: String, address: String, cityprovince: String, postalcode: String, studentid: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {
        Text(
            text = "Account Details",
            fontSize = 40.sp
        )
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "User Profile Pic"
        )
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(top = 20.dp)
        )
        {
            Text(
                text = "First Name: $firstname",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "Last Name: $lastname",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "Email: $email",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "Address: $address",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "City/Province: $cityprovince",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "Postal Code: $postalcode",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "Student Id: $studentid",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }
    }
}