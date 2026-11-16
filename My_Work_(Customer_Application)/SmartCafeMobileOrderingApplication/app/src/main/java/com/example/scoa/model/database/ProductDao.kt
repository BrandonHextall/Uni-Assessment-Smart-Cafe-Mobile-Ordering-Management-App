package com.example.scoa.model.database

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.example.scoa.model.entity.ProductEntity

@Dao
interface ProductDao {

    // Insert a product
    @Insert
    suspend fun insertProduct(product: ProductEntity)

    // Insert several products
    @Insert
    suspend fun insertProducts(products: List<ProductEntity>)

    // Retrieve products
    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductEntity>

    // Get product price
    @Query("SELECT price FROM products WHERE productID = :productID")
    suspend fun getProductPrice(productID: Int): Double

    // Retrieve product by ID
    @Query("SELECT * FROM products WHERE productID = :productID")
    suspend fun getProductById(productID: Int): ProductEntity?

    // Update existing product
    @Update
    suspend fun updateProduct(product: ProductEntity)

    // Delete a product
    @Delete
    suspend fun deleteProduct(product: ProductEntity)
}