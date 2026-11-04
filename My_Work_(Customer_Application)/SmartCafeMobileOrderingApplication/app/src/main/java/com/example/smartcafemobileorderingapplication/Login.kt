package com.example.smartcafemobileorderingapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class Login : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                LoginScreen(
                    onLogin = { email, password ->
                        // Temporary test response
                        Toast.makeText(
                            this,
                            "Login button pressed for $email",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    onLogin: (String, String) -> Unit,
    //onRegisterClick: () -> Unit
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            "Smart Café",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        OutlinedTextField(
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

        Button(
            onClick = {
                error = if (
                    email.isBlank() || password.isBlank()
                ) {
                    "Enter email and password"
                } else {
                    ""
                }

                if (error.isEmpty()) {
                    onLogin(email.trim(), password)
                }
            }
        ) {
            Text("Sign in")
        }
    }
}
