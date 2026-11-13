package com.example.scoa.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Temporary while converting to MVVM format

// Firebase

// Application screens
import com.example.scoa.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen (
    vm: RegisterViewModel,
    onLoginClick: () -> Unit,
    onRegistrationSuccess: () -> Unit
) {
    val navController = rememberNavController()

    // Initiate variables for registration credential storage
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    var deliveryAddress by rememberSaveable { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // Allows navigation to login screen
    NavHost (
        navController = navController,
        startDestination = "empty"
    ) {
        // Empty composable to prevent simultaneous rendering of login and register screens
        composable("empty") {}

        composable("login") {
            LoginScreen (
                onLoginSuccess = {
                    // Navigate to home screen
                    navController.navigate("home")
                }
            )
        }
    }

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
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button (
            onClick = {
                isLoading = true
                error = ""
                vm.register(fullName, email, password, repeatPassword, deliveryAddress)

            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            // Buffer icon while loading
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            } else Text("Register")
        }

        /*
        * Accept terms and conditions portion
        * TODO
        * */

        // Displays clickable link to switch to login page
        Button (onClick = onLoginClick) {
            Text("Already have an account? Login here")
        }
    }
}