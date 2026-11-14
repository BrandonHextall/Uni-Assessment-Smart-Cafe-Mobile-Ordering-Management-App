package com.example.scoa.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.scoa.viewmodel.LoginViewModel


@Composable
fun LoginScreen (
    // Receives authentication repository through constructor
    viewModel: LoginViewModel,
    navController: NavController
) {
    // Observes error message from ViewModel
    // Auto-updates when value change
    val loggedIn by viewModel.loggedIn.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    // Store inputs as variables
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Runs whenever login status changes
    // LaunchedEffect can run suspend (pausable) functions
    LaunchedEffect(loggedIn) {
        if (loggedIn) {
            // Navigate to home screen
            navController.navigate("home") {

                // Remove previous destinations from backstack
                // (basically back button history)
                popUpTo("login") { inclusive = true }

                // Prevents multiple instances of home screen
                launchSingleTop = true
            }
        }
    }

    // Login screen layout

    // Add placeholder image to page
    Column (modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),) {
        // Email field
        OutlinedTextField (
            value = email,
            // When value changes, update variable to new value
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        // Password field
        OutlinedTextField (
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        // Login button
        Button (
            onClick = { viewModel.login(email, password) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            if (isLoading) CircularProgressIndicator(modifier = Modifier.size(20.dp))
            else Text("Login")
        }

        // Clickable link to switch to register page
        Button (onClick = {
            navController.navigate("register") {
                // Remove previous destinations from backstack
                // (basically back button history)
                popUpTo(0) { inclusive = true }

                // Prevents multiple instances of home screen
                launchSingleTop = true
            }
        }) { Text("Don't have an account? Register here") }

        // Displays error message when one exists
        if (error.isNotEmpty()) Text(error)
    }
}