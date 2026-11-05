package com.example.smartcafemobileorderingapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun RegisterScreen (
    onRegister: (String, String) -> Unit,
) {

    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    var deliveryAddress by rememberSaveable { mutableStateOf("") }
    var error by remember { mutableStateOf("") }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text (
            "Register",
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField (
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full Name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
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

        OutlinedTextField (
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        OutlinedTextField (
            value = deliveryAddress,
            onValueChange = { deliveryAddress = it },
            label = { Text("Delivery Address") },
            singleLine = true,
        )

        if (error.isNotEmpty()) {
            Text(
                error,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button (
            onClick = {
                error = if (
                    email.isBlank() || password.isBlank()
                ) {
                    "Please fill in all fields"
                } else if (
                    password != repeatPassword
                ) {
                    "Passwords do not match"
                } else if (
                    password.length < 8
                ) {
                    "Password must be at least 8 characters long"
                }
                else if (
                    !email.contains("@")
                ) {
                    "Invalid email"
                } else {
                    ""
                }

                if (error.isEmpty()) {
                    onRegister(email.trim(), password)
                }
            }
        ) {
            Text("Register")
        }

        Button (
            onClick = {
                onRegister("", "")
            }
        ) {
            Text("Register with Google")
        }
    }
}
