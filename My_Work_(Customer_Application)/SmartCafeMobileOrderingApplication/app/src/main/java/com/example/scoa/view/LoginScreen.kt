package com.example.scoa.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scoa.viewmodel.LoginViewModel


@Composable
fun LoginScreen (
    // Receives authentication repository through constructor
    viewModel: LoginViewModel = viewModel(),

    // Callback executed when user successfully logs in (navigation handled outside ViewModel
    onLoginSuccess: () -> Unit
) {
    // Observes error message from ViewModel
    // Auto-updates when value changes
    val error by viewModel.error.collectAsStateWithLifecycle()

    // Observes whether user has successfully logged in
    val loggedIn by viewModel.loggedIn.collectAsStateWithLifecycle()

    // Store inputs as variables
    var email by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}

    // Runs whenever login status changes
    // LaunchedEffect can run suspend (pausable) functions
    LaunchedEffect(loggedIn) {
        if (loggedIn) onLoginSuccess()
    }

    // Login screen layout
    Column {
        // Email field
        OutlinedTextField(
            value = email,
            // When value changes, update variable to new value
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        // Login button
        Button (
            onClick = { viewModel.login(email, password) }
        ) {
            Text("Login")
        }

        // Displays error message when one exists
        if (error.isNotEmpty()) Text(error)
    }
}