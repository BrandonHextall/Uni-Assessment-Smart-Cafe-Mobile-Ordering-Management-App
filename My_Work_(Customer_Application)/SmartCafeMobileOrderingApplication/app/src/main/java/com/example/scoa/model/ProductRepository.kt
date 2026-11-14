package com.example.scoa.model

import com.google.firebase.firestore.FirebaseFirestore

// Retrieves products from Firestore
class ProductRepository {
    private val db = FirebaseFirestore.getInstance()

    // Retrieve all products from Firestore
    fun getProducts(onResult: (Result<List<Product>>) -> Unit) {
        db.collection("menu")
            .get()
            .addOnSuccessListener { docs ->

                // Converts each document into a Product
                var products = docs.mapNotNull { doc ->
                    doc.toObject(Product::class.java)
                }
                // Adds image filename to Product
                products.forEach { product ->
                    product.imageName = product.name.lowercase().replace(" ", "_").replace("&_", "")
                }

                onResult(Result.success(products))
            }
            .addOnFailureListener { exception ->
                onResult(Result.failure(exception))
            }
    }
}