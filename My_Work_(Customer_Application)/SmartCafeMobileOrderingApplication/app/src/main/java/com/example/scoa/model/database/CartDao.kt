package com.example.scoa.model.database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import com.example.scoa.model.entity.CartEntity
import com.example.scoa.model.entity.CartItemEntity

@Dao
interface CartDao {

    // -----------------------
    // CART OPERATIONS
    // -----------------------

    // Create a cart
    @Insert
    suspend fun insertCart(cart: CartEntity): Long

    // Find customer cart by Firebase UID
    @Query("SELECT * FROM carts WHERE userID = :userID LIMIT 1")
    suspend fun getCartByUserId(userID: Int): CartEntity?

    // Find cart by its ID
    @Query("SELECT * FROM carts WHERE cartID = :cartID")
    suspend fun getCartByID(cartID: Int): CartEntity?

    // -----------------------
    // CART ITEM OPERATIONS
    // -----------------------

    // Add product to cart
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToCart(cartItem: CartItemEntity): Long

    // Retrieve all products belonging to a cart
    @Query("SELECT * FROM cart_items WHERE cartID = :cartID")
    suspend fun getCartItems(cartID: Int): List<CartItemEntity>

    // Retrieve specific product from cart
    @Query("SELECT * FROM cart_items WHERE cartID = :cartID AND productID = :productID LIMIT 1")
    suspend fun getCartItem(cartID: Int, productID: Int): CartItemEntity?

    // Update product quantity
    @Query("UPDATE cart_items SET quantity = :quantity WHERE cartID = :cartID AND productID = :productID")
    suspend fun updateCartItemQuantity(cartID: Int, productID: Int, quantity: Int)

    // Remove product from cart
    @Query("DELETE FROM cart_items WHERE cartID = :cartID AND productID = :productID")
    suspend fun removeFromCart(cartID: Int, productID: Int)

    // Remove all products from cart
    @Query("DELETE FROM cart_items WHERE cartID = :cartID")
    suspend fun clearCart(cartID: Int)

    // Get every cart item
    @Query("SELECT * FROM cart_items")
    suspend fun getAllCartItems(): List<CartItemEntity>

    // -----------------------
    // TESTING ONLY
    // -----------------------
    // Gets all available carts
    @Query("SELECT * FROM carts")
    suspend fun getAllCarts(): List<CartEntity>
}