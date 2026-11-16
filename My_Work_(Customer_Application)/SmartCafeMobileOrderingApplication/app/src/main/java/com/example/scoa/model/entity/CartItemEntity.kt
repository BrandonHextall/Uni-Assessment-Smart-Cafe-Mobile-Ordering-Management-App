package com.example.scoa.model.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "cart_items",
    indices = [Index(value = ["cartID", "productID"], unique = true), ])
data class CartItemEntity (
    @PrimaryKey(autoGenerate = true)
    val cartItemID: Int = 0,
    @ColumnInfo("cartID")
    val cartID: Int,
    @ColumnInfo("productID")
    val productID: Int,
    @ColumnInfo("quantity")
    val quantity: Int = 0
)