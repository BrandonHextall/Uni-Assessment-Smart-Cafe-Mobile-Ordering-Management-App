package com.example.scoa.viewmodel

import androidx.lifecycle.ViewModel
import com.example.scoa.model.FirebaseManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    private val firebase = FirebaseManager()

    private val _error = MutableStateFlow("")
    private val _loggedIn = MutableStateFlow(false)

    // Makes error and loggedIn available as read-only
    // The View can observe the changes but cannot modify it
    val error: StateFlow<String> = _error
    val loggedIn: StateFlow<Boolean> = _loggedIn

    fun login(email: String, password: String) {
        // Checks if inputs are blank
        if (email.isBlank() || password.isBlank()) {
            _error.value = "Fill in all fields"
            return
        }

        firebase.login(email.trim(), password) { result ->
            result.onSuccess {
                _loggedIn.value = true
                _error.value = ""
            }
            // The ?.message only collects the string if it available
            // Helps prevent errors and exceptions
            result.onFailure {
                _error.value = result.exceptionOrNull()?.message ?: "Login failed"
            }
        }
    }
}