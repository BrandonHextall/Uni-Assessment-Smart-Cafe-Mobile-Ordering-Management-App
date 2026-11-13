package com.example.scoa.model

import com.google.firebase.auth.FirebaseAuth

class FirebaseManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login (
        email: String,
        password: String,
        onResult: (Result<Unit>) -> Unit
    ) {
        // Cut-down version of previous login function
        auth.signInWithEmailAndPassword(email.trim(), password)
            .addOnSuccessListener {
                onResult(Result.success(Unit))
            }
            .addOnFailureListener { exception ->
                onResult(Result.failure(exception))
            }
    }

    fun register (
        email: String,
        password: String,
        onResult: (Result<String>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { task ->
                // Get user ID
                val uid = auth.currentUser?.uid

                // If UID is not null, return success
                if (uid != null) {
                    onResult(Result.success(uid))
                } else {
                    onResult (Result.failure(Exception("Unable to retrieve user ID")))
                }
            }
            .addOnFailureListener { task ->
                onResult (
                    Result.failure(Exception("Registration failed"))
                )
            }
    }

    fun logout() {
        auth.signOut()
    }

    // Checks whether user is logged in
    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}