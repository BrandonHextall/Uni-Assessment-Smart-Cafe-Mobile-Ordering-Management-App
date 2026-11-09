package com.example.smartcafemobileorderingapplication.model

// Handles Firebase Authentication
interface AuthRepository {
    fun signIn (
        email: String,
        password: String,
        onResult: (Result<Unit>) -> Unit
    )
}
