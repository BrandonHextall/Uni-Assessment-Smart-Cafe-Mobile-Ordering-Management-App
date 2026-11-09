package com.example.scoa.controller

import com.example.scoa.model.AuthRepository
import com.example.scoa.model.UserRepository

class LoginController(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    fun login(
        email: String,
        password: String,
        onResult: (Result<Unit>) -> Unit
    ) {

        // Validate inputs
        if (email.isBlank() || password.isBlank()) {
            onResult(
                Result.failure(
                    Exception("Enter email and password")
                )
            )
            return
        }

        authRepository.signIn(email.trim(), password) { result -> onResult(result) }
    }
}