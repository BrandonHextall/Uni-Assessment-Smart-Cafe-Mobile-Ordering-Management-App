package com.example.scoa.model.repository

import com.example.scoa.model.database.ProductDao
import com.example.scoa.model.entity.ProductEntity
import com.example.scoa.model.initialProducts

// Retrieves products from database
class ProductRepository (private val productDao: ProductDao) {

    // Populate the database if it doesn't contain any products
    suspend fun initialiseProducts() {

        val existingProducts = productDao.getAllProducts()

        // Only insert products if the table is empty
        if (existingProducts.isEmpty()) {
            productDao.insertProducts(initialProducts)
        }
    }

    // Insert a product
    suspend fun insertProduct(product: ProductEntity) {
        productDao.insertProduct(product)
    }

    // Insert multiple products
    suspend fun insertProducts(products: List<ProductEntity>) {
        productDao.insertProducts(products)
    }

    // Retrieve all products
    suspend fun getAllProducts(): List<ProductEntity> {
        return productDao.getAllProducts()
    }

    // Get product price
    suspend fun getProductPrice(id: Int): Double {
        return productDao.getProductPrice(id)
    }


    // Retrieve product by ID
    suspend fun getProductById(id: Int): ProductEntity? {
        return productDao.getProductById(id)
    }

    // Update existing product
    suspend fun updateProduct(product: ProductEntity) {
        productDao.updateProduct(product)
    }

    // Delete a product
    suspend fun deleteProduct(product: ProductEntity) {
        productDao.deleteProduct(product)
    }
}