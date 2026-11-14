package com.example.scoa.model.entity

data class Cartentity (
    val cartID: String = "",
    val userID: String = "",
    val products: Map<ProductEntity, Int>
)