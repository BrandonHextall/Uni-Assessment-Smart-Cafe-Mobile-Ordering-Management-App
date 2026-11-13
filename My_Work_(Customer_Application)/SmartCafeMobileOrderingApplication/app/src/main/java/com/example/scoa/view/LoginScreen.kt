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
import com.example.scoa.controller.FirebaseAuthRepository
import com.example.scoa.controller.LoginController
import com.example.scoa.controller.RegisterController
import com.example.scoa.model.UserRepository
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    controller: LoginController,
    onRegisterClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val navController = rememberNavController()

    // Initiate variables for login credential storage
    var email by rememberSaveable() { mutableStateOf("") }
    var password by rememberSaveable() { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // Allows navigation to login screen
    NavHost (
        navController = navController,
        startDestination = "empty"
    ) {
        // Empty composable to prevent simultaneous rendering of login and register screens
        composable("empty") {}

        composable("register") {
            RegisterScreen (
                controller = remember {
                    RegisterController(
                        authRepository = FirebaseAuthRepository(
                            FirebaseAuth.getInstance()
                        ),
                        userRepository = UserRepository()
                    )
                },
                onLoginClick = {
                    navController.navigate("login")
                },
                onRegistrationSuccess = {
                    // Navigate to home screen
                    navController.navigate("home")
                }
            )
        }
    }

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
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button (
            onClick = {
                isLoading = true
                error = ""

                controller.login(email, password) { result ->
                    isLoading = false
                    result.onSuccess {
                        onLoginSuccess()
                    }
                    result.onFailure {
                        error = it.message ?: "Login failed"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            // Buffer icon for loading
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            } else Text("Sign in")
        }

        // Displays clickable link to switch to register page
        Button (onClick = onRegisterClick) {
            Text("Already have an account? Login here")
        }
    }
}
