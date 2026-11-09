package com.example.scoa.model

// Handles Firebase Authentication
interface AuthRepository {
    fun signIn (
        email: String,
        password: String,
        onResult: (Result<Unit>) -> Unit
    )

    fun register (
        email: String,
        password: String,
        onResult: (Result<String>) -> Unit
    )
}
