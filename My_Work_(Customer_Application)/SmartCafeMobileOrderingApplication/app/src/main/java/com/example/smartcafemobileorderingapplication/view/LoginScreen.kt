package com.example.smartcafemobileorderingapplication.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLogin: (String, String) -> Unit
) {
    var email by rememberSaveable() {
        mutableStateOf("")
    }

    var password by rememberSaveable() {
        mutableStateOf("")
    }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text (
            "Login",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField (
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        OutlinedTextField (
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        if (error.isNotEmpty()) {
            Text(
                error,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button (
            onClick = {
                onLogin(email, password)
            }
        ) {
            Text("Sign in")
        }

        Button (
            onClick = {
                //GoogleLogin()
            }
        ) {
            Text("Login with Google")
        }
    }
}
