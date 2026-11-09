package com.example.smartcafemobileorderingapplication.controller
import com.example.smartcafemobileorderingapplication.model.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthRepository (
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override fun signIn (
        email: String,
        password: String,
        onResult: (Result<Unit>) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(Result.success(Unit))
            } else {
                onResult(Result.failure(task.exception ?: Exception("Login failed")))
            }
        }
    }
}