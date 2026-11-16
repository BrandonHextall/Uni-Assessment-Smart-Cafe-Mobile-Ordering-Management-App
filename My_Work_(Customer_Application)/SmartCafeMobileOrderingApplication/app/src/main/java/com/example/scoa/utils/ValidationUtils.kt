package com.example.scoa.utils

import android.util.Patterns
val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

fun validateLogin(
    email: String,
    password: String
): String? {

    if (email.isBlank() && password.isBlank()) return "Fill in all fields"

    if (email.isBlank()) return "Please enter your email address"

    if (password.isBlank()) return "Please enter your password"

    if (!EMAIL_REGEX.matches(email)) return "Please enter a valid email address"

    return null
}

fun validateRegistration(
    fullName: String,
    email: String,
    password: String,
    repeatPassword: String,
    address: String
): String? {

    if (fullName.isBlank() || email.isBlank() || password.isBlank() || repeatPassword.isBlank() || address.isBlank()) return "Fill in all fields"

    if (!EMAIL_REGEX.matches(email)) return "Please enter a valid email address"

    if (password.length < 6) return "Password must be at least 6 characters"

    if (password != repeatPassword) return "Passwords do not match"

    return null
}