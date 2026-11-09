package com.example.scoa.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scoa.pinyonScript

@Composable
fun StartPage(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onBrowseMenuClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Welcome to Liber-tea Café!",
            color = Color.White,
            fontFamily = pinyonScript,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 50.dp)
        )

        Button(onClick = onLoginClick) {
            Text("Login")
        }

        Button(onClick = onRegisterClick) {
            Text("Register")
        }

        Button(onClick = onBrowseMenuClick) {
            Text("Browse Menu")
        }
    }
}