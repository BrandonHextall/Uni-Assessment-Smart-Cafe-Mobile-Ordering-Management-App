package com.example.scoa.model.repository

import android.util.Log
import com.example.scoa.model.database.CartDao
import com.example.scoa.model.database.ProductDao
import com.example.scoa.model.database.UserDao
import com.example.scoa.model.entity.CartEntity
import com.example.scoa.model.entity.CartItemEntity
import com.example.scoa.model.entity.ProductEntity
import com.google.firebase.auth.FirebaseAuth

class CartRepository (
    private val cartDao: CartDao,
    private val productDao: ProductDao,
    private val userDao: UserDao
) {

    // -----------------------
    // CART OPERATIONS
    // -----------------------

    // Create new cart
    suspend fun insertCart(cart: CartEntity) { cartDao.insertCart(cart) }

    // Find customer cart by Firebase UID
    suspend fun getCartByUserID(userID: Int): CartEntity? { return cartDao.getCartByUserId(userID) }

    // Find cart by its ID
    suspend fun getCartByID(cartID: Int): CartEntity? { return cartDao.getCartByID(cartID) }

    // -----------------------
    // CART ITEM OPERATIONS
    // -----------------------

    // Add product to cart
    suspend fun addToCart(productID: Int, quantity: Int, price: Double): Boolean {
        Log.d("USER_DEBUG", "All users: ${userDao.getAllUsers()}")
        // Get current user ID
        val firebaseUser = FirebaseAuth.getInstance().currentUser ?: return false
        val uid = firebaseUser.uid
        Log.d("USER_DEBUG", "User ID: $uid")

        Log.d("USER_DEBUG", "User by UID: ${userDao.getUserByUid(uid)}")
        // Get user by Firebase UID
        val user = userDao.getUserByUid(uid) ?: return false

        // Get user's local ID
        val localID = user.userID
        Log.d("USER_DEBUG", "User local ID: $localID")

        // Get user's associated cart
        var cart = cartDao.getCartByUserId(localID)
        Log.d("CART_DEBUG", "User cart: $cart")


        // Create basket if one doesn't exist
        if (cart == null) {
            val newCartID = cartDao.insertCart(CartEntity(userID = localID))

            // assign cart variable the new cart ID
            cart = cartDao.getCartByID(newCartID.toInt()) ?: return false
        }
        val cartID = cart.cartID

        // Check if product exists in cart
        val existingItem = cartDao.getCartItem(cartID, productID)
        if (existingItem != null) {
            // Increase quantity
            cartDao.updateCartItemQuantity(cartID, productID, existingItem.quantity + quantity)
        } else {
            // Insert new item
            val result = cartDao.addToCart(
                CartItemEntity(
                    cartID = cartID,
                    productID = productID,
                    quantity = quantity,
                    price = price
                )
            )
            Log.d("CART_DEBUG", "Insert result: $result")
        }
        return true
    }

    // Retrieve all products belonging to a cart
    suspend fun getCartItemsByCartID(): List<CartItemEntity> {
        println("------------------------------- getCartItemsByCartID -------------------------------")
        val firebaseUser = FirebaseAuth.getInstance().currentUser ?: return emptyList()
        Log.d("CART_DEBUG", "Firebase user: $firebaseUser")
        val uid = firebaseUser.uid
        Log.d("CART_DEBUG", "Firebase UID: $uid")
        val user = userDao.getUserByUid(uid) ?: return emptyList()
        Log.d("CART_DEBUG", "User: $user")
        val localID = user.userID
        Log.d("CART_DEBUG", "Local ID: $localID")
        val cart = cartDao.getCartByUserId(localID) ?: return emptyList()
        Log.d("CART_DEBUG", "Cart: $cart")
        println("------------------------------------------------------------------------------------------")

        return cartDao.getCartItems(cart.cartID)
    }

    // Retrieve specific product from cart
    suspend fun getCartItem(cartID: Int, productID: Int): CartItemEntity? {
        return cartDao.getCartItem(cartID, productID)
    }

    // Update product quantity
    suspend fun updateCartItemQuantity(cartID: Int, productID: Int, quantity: Int) {
        cartDao.updateCartItemQuantity(cartID, productID, quantity)
    }

    // Remove product from cart
    suspend fun removeFromCart(productID: Int): Boolean {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
            ?: return false

        val user = userDao.getUserByUid(firebaseUser.uid)
            ?: return false

        val cart = cartDao.getCartByUserId(user.userID)
            ?: return false

        cartDao.removeFromCart(cart.cartID, productID)

        return true
    }

    // Remove all products from cart
    suspend fun clearCart(cartID: Int) {
        cartDao.clearCart(cartID)
    }

    // Get total value of cart items
    suspend fun getCartCost(cartID: Int): Double {
        var total: Double = 0.0
        val cartItems = cartDao.getCartItems(cartID)

        // Debug stuff
        Log.d("CART_DEBUG", "Cart items: $cartItems")

        cartItems.forEach { item ->
            val product = productDao.getProductById(item.productID)
            val price = product?.price ?: 0.0
            total += price * item.quantity
        }
        return total
    }

    // Get all cart items in table
    suspend fun getAllCartItems(): List<CartItemEntity> {
        return cartDao.getAllCartItems()
    }

    // Get cart items with associated product entity
    suspend fun getCartItemsWithProducts(cartItems: List<CartItemEntity>): List<Pair<CartItemEntity, ProductEntity>> {
        return cartItems.mapNotNull { item ->
            val product = productDao.getProductById(item.productID)
            if (product != null) {
                Pair(item, product)
            } else {
                null
            }
        }
    }

    // -----------------------
    // TESTING ONLY
    // -----------------------
    // Gets all available carts
    suspend fun getAllCarts(): List<CartEntity> { return cartDao.getAllCarts() }
}