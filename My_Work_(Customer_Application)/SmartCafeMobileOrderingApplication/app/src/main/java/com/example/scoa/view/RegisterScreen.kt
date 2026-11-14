package com.example.scoa.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.scoa.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen (
    viewModel: RegisterViewModel,
    navController: NavController
) {
    // Initiate variables for registration credential storage
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    var deliveryAddress by rememberSaveable { mutableStateOf("") }

    // Observes error message from ViewModel
    // Auto-updates when value change
    val loggedIn by viewModel.loggedIn.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    // Navigates to home screen when registration is successful
    // Not 100% sure if this is necessary
    LaunchedEffect(loggedIn) {
        if (loggedIn) {
            navController.navigate("home") {
                popUpTo("home") { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    // Register screen layout

    // Add placeholder image to page
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedTextField (
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
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
            Text (
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }

        /*
        * Accept terms and conditions portion
        * TODO
        * */

        // Register button
        Button (
            onClick = { viewModel.register(fullName, email, password, repeatPassword, deliveryAddress) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            // Buffer icon while loading
            if (isLoading) CircularProgressIndicator(modifier = Modifier.size(20.dp))
            else Text("Register")
        }

        // Displays clickable link to switch to login page
        Button (onClick = {
            navController.navigate("login") {

                // Remove previous destinations from backstack
                // (basically back button history)
                popUpTo("home") { inclusive = true }

                // Prevents multiple instances of home screen
                launchSingleTop = true
            }
        }) { Text("Already have an account? Login here") }
    }
}