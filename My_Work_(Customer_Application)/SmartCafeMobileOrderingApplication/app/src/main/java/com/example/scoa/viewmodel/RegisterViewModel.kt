package com.example.scoa.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scoa.model.authentication.FirebaseManager
import com.example.scoa.model.database.CafeDatabase
import com.example.scoa.model.entity.UserEntity
import com.example.scoa.model.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel (application: Application) : AndroidViewModel(application) {

    // Gets Room database instance
    private val database = CafeDatabase.getDatabase(application)

    // Create repository using UserDao
    private val userRepository = UserRepository(database.userDao())

    // Create Firebase Auth manager
    private val firebase = FirebaseManager()

    // Stores any errors that occur
    private val _error = MutableStateFlow("")

    // Tracks login state
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

        // Launch registration as asynchronous coroutine
        viewModelScope.launch {
            firebase.register(email.trim(), password) { result ->
                result.onSuccess { uid ->

                    // Launch saveUser as asynchronous coroutine
                    viewModelScope.launch {

                        // SaveUser placed in try-catch to prevent crashing
                        try {
                            val user = UserEntity(
                                firebaseUID = uid,
                                fullName = fullName,
                                email = email,
                                deliveryAddress = address
                            )

                            // Attempt to save user to local database
                            userRepository.saveUser(user)


                            _loggedIn.value = true

                            // Reports error if SQLite fails
                        } catch (e: Exception) {
                            _error.value = e.message ?: "Unable to save customer details"

                            // Stops loading indicator once database operation finished
                        } finally {
                            _isLoading.value = false
                        }
                    }
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
}