package com.example.scoa.controller
import com.example.scoa.model.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthRepository (
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override fun signIn (
        email: String,
        password: String,
        onResult: (Result<Unit>) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email.trim(), password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(Result.success(Unit))
            } else {
                val exception = task.exception
                val errorMessage = when (exception) {
                    is com.google.firebase.auth.FirebaseAuthInvalidUserException -> "Invalid email or password"
                    is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException -> "Invalid email or password"
                    else -> exception?.message ?: "Sign-in failed"
                }
                onResult(Result.failure(Exception(errorMessage)))
            }
        }
    }

    override fun register (
        email: String,
        password: String,
        onResult: (Result<String>) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = firebaseAuth.currentUser?.uid

                    if (uid != null) {
                        onResult(Result.success(uid))
                    } else {
                        onResult (Result.failure(Exception("Unable to retrieve user ID")))
                    }
                } else {
                    onResult (
                        Result.failure(task.exception ?: Exception("Registration failed"))
                    )
                }
            }
    }
}