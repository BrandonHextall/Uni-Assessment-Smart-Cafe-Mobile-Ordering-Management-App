package com.example.scoa.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scoa.model.database.CafeDatabase
import com.example.scoa.model.entity.ProductEntity
import com.example.scoa.model.entity.UserEntity
import com.example.scoa.model.initialProducts
import com.example.scoa.model.repository.ProductRepository
import com.example.scoa.model.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    // Declate database
    private val db = CafeDatabase.getDatabase(application)

    private val productRepository = ProductRepository(db.productDao())
    private val userRepository = UserRepository(db.userDao(), db.cartDao())


    // MutableStateFlow stores the data
    // StateFlow exposes the data
    private val _products = MutableStateFlow<List<ProductEntity>>(emptyList())
    val products: StateFlow<List<ProductEntity>> = _products

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error


    private val _users = MutableStateFlow<List<UserEntity>>(emptyList())
    val users: StateFlow<List<UserEntity>> = _users

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            Log.d("PROD_DEBUG", "Initial products: ${initialProducts.size}")
            try {// Check how many predefined products exist
                Log.d("PROD_DEBUG", "Initial products: ${initialProducts.size}")
                // Populate database if it is currently empty
                productRepository.initialiseProducts()

                // Retrieve products from database
                _products.value = productRepository.getAllProducts()
            } catch (e: Exception) {
                _error.value = e.message ?: "Unable to retrieve products"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadUsers() {
        viewModelScope.launch {
            try {
                _users.value = userRepository.getAllUsers()
            } catch (e: Exception) {
                _error.value = e.message ?: "Unable to retrieve users"
            }
        }
    }
}
