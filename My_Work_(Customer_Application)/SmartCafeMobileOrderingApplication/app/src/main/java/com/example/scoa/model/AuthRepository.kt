package com.example.scoa.model

import com.google.firebase.auth.FirebaseAuth

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

fun isUserLoggedIn(): Boolean {
    return FirebaseAuth.getInstance().currentUser != null
}
