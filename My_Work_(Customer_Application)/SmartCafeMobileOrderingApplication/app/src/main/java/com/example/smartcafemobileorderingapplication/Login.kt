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
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import androidx.compose.ui.res.stringResource

@Composable
fun GoogleLogin() {
    val googleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(true)
        .setServerClientId(stringResource(R.string.default_web_client_id))
        .build()

    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
            .build()
}
@Composable
fun LoginScreen (
    onLogin: (String, String) -> Unit,
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

        Button (
            onClick = {
                //GoogleLogin()
            }
        ) {
            Text("Login with Google")
        }
    }
}
