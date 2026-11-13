package com.example.scoa.viewmodel

import androidx.lifecycle.ViewModel
import com.example.scoa.model.Product
import com.example.scoa.model.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    // Manage product data here
    // Manage error states
    private val productRepository = ProductRepository()

    // MutableStateFlow stores the data
    // StateFlow exposes the data
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    private val _isLoading = MutableStateFlow(false)
    private val _error = MutableStateFlow("")

    // Makes states read-only
    val products: StateFlow<List<Product>> = _products
    val isLoading: StateFlow<Boolean> = _isLoading
    val error: StateFlow<String> = _error

    // Automatically retrieves products when ViewModel is created
    init {
        loadProducts()
    }

    // Can be called again later to refresh products
    fun loadProducts() {
        _isLoading.value = true
        _error.value = ""

        // Calls getProducts from ProductRepository
        productRepository.getProducts { result ->
            result.onSuccess { productList ->
                _products.value = productList
            }

            result.onFailure { exception ->
                _error.value = exception.message ?: "Unable to retrieve products"
            }

            _isLoading.value = false
        }
    }
}
