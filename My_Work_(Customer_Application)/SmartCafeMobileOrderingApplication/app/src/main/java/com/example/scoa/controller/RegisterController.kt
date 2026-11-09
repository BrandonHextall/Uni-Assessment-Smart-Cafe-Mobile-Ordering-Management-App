package com.example.scoa.controller

import com.example.scoa.model.AuthRepository
import com.example.scoa.model.User
import com.example.scoa.model.UserRepository

class RegisterController (
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    fun register (
        fullName: String,
        email: String,
        password: String,
        repeatPassword: String,
        deliveryAddress: String,
        onResult: (Result<Unit>) -> Unit
    ) {

        // Validate inputs
        if (fullName.isBlank() || email.isBlank() || password.isBlank() || repeatPassword.isBlank() || deliveryAddress.isBlank()) {
            onResult(Result.failure(Exception("Please fill in all fields")))
            return
        }

        // Validate email
        if (!android.util.Patterns.EMAIL_ADDRESS
            .matcher(email.trim()).matches()
            ) {
            onResult(Result.failure(Exception("Please enter a valid email address")))
            return
        }

        // Validate password length
        if (password.length < 8) {
            onResult(Result.failure(Exception("Password must be at least 8 characters long")))
            return
        }

        // Validate passwords match
        if (password != repeatPassword) {
            onResult(Result.failure(Exception("Passwords do not match")))
            return
        }

        // Create Firebase Authentication account
        authRepository.register(
            email.trim(),
            password
        ) {
            authResult -> authResult.onSuccess { uid ->
                // Create customer model
                val user = User(
                    userID = uid,
                    fullName = fullName.trim(),
                    email = email.trim(),
                    deliveryAddress = deliveryAddress.trim()
                )

                // Save customer to Firestore
            userRepository.saveUser(user) { saveResult ->
                onResult(saveResult)
            }
        }
            authResult.onFailure { e ->
                onResult(Result.failure(e))
            }
        }
    }
}