package com.example.scoa.model.repository

import com.example.scoa.model.database.ProductDao
import com.example.scoa.model.entity.ProductEntity
import com.example.scoa.model.initialProducts
import com.google.firebase.firestore.FirebaseFirestore

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
class ProductRepositoryOld {
    private val db = FirebaseFirestore.getInstance()

    // Retrieve all products from Firestore
    fun getProducts(onResult: (Result<List<ProductEntity>>) -> Unit) {
        db.collection("menu")
            .get()
            .addOnSuccessListener { docs ->

                // Converts each document into a Product
                var products = docs.mapNotNull   { doc ->
                    doc.toObject(ProductEntity::class.java)
                }
                // Adds image filename to Product
                products.forEach { product ->
                    //product.imageName = product.name.lowercase().replace(" ", "_").replace("&_", "")
                }

                onResult(Result.success(products))
            }
            .addOnFailureListener { exception ->
                onResult(Result.failure(exception))
            }
    }
}