package com.example.scoa.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scoa.model.entity.CartItemEntity
import com.example.scoa.model.entity.ProductEntity
import com.example.scoa.model.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*
    Functions:
        Retrieving the customer's cart.
        Creating a cart if one doesn't exist.
        Adding products.
        Removing products.
        Updating quantities.
        Calculating the total.
        Clearing the cart.
        Exposing cart contents to Compose.
*/

class CartViewModel (private val cartRepository: CartRepository): ViewModel() {
    // Tracks whether product was successfully added
    private val _addSuccess = MutableStateFlow<Boolean>(false)
    val addSuccess: StateFlow<Boolean> = _addSuccess.asStateFlow()

    // Tracks whether the operation is currently running
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Stores cart data
    private val _cartContents = MutableStateFlow<List<CartItemEntity>>(emptyList())
    val cartContents: StateFlow<List<CartItemEntity>> = _cartContents.asStateFlow()

    fun addToCart(productID: Int, quantity: Int, price: Double) {
        Log.d("CART_DEBUG", "Product: $productID, Quantity: $quantity")
        if (quantity < 1) return
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _addSuccess.value = cartRepository.addToCart(productID, quantity, price)
        }
    }

    fun loadCart() {
        viewModelScope.launch(Dispatchers.IO) {
            println("------------------------------ loadCart() ------------------------------")
            _isLoading.value = true
            try {
                Log.d("CART_DEBUG", "getCartItems value: ${cartRepository.getCartItemsByCartID().toList()}")
                _cartContents.value = cartRepository.getCartItemsByCartID()
                Log.d("CART_DEBUG", "cartContents value: ${cartContents.value}")

            } catch (e: Exception) {
                _cartContents.value = emptyList()
                Log.e("CART_DEBUG", "Error loading cart: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Resets result after displaying feedback
    fun resetAddState() {
        _addSuccess.value = false
    }
}