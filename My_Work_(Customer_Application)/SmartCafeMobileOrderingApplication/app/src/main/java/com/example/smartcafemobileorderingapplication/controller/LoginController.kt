package com.example.smartcafemobileorderingapplication.controller

import com.example.smartcafemobileorderingapplication.model.AuthRepository

class LoginController(
    private val authRepository: AuthRepository
) {
    fun login(
        email: String,
        password: String,
        onResult: (Result<Unit>) -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            onResult(
                Result.failure(
                    Exception("Enter email and password")
                )
            )
            return
        }

        authRepository.signIn(email.trim(), password, onResult)
    }
}