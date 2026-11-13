package com.example.scoa.viewmodel

import androidx.lifecycle.ViewModel
import com.example.scoa.model.FirebaseManager
import com.example.scoa.model.User
import com.example.scoa.model.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel : ViewModel() {
    private val firebase = FirebaseManager()
    private val userRepository = UserRepository()
    private val _error = MutableStateFlow("")
    private val _loggedIn = MutableStateFlow(false)
    private val _isLoading = MutableStateFlow(false)

    // Makes error and loggedIn available in read-only
    // The View can observe the changes but cannot modify it
    val error: StateFlow<String> = _error
    val loggedIn: StateFlow<Boolean> = _loggedIn
    val isLoading: StateFlow<Boolean> = _isLoading

    fun register(fullName: String, email: String, password: String, repeatPassword: String, address: String) {
        // Prevents multiple registration attempts
        if (_isLoading.value) return

        // Checks for blank inputs
        if (fullName.isBlank() || email.isBlank() || password.isBlank() || repeatPassword.isBlank() || address.isBlank()) {
            _error.value = "Fill in all fields"
            return
        }

        // Validate email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            _error.value = "Please enter a valid email address"
            return
        }

        // Validate password length
        if (password.length < 6) {
            _error.value = "Password must be at least 6 characters long"
            return
        }

        // Validate passwords match
        if (password != repeatPassword) {
            _error.value = "Passwords do not match"
            return
        }

        _isLoading.value = true
        _error.value = ""

        firebase.register(email.trim(), password) { result ->
            result.onSuccess { uid ->
                // Create customer model
                val user = User (
                    userID = uid,
                    fullName = fullName.trim(),
                    email = email.trim(),
                    deliveryAddress = address.trim()
                )

                // Save user to Firestore
                userRepository.saveUser(user) {
                    saveResult -> saveResult.onSuccess {
                        _loggedIn.value = true
                    }
                    saveResult.onFailure {
                        _error.value = saveResult.exceptionOrNull()?.message ?: "Unable to save user data"
                    }
                }

                _isLoading.value = false
            }
            // The ?.message only collects the string if it available
            // Helps prevent errors and exceptions
            result.onFailure {
                _error.value = result.exceptionOrNull()?.message ?: "Registration failed"
                _isLoading.value = false
            }
        }
    }
}