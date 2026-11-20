package com.example.scoa.model.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val orderID: Int,
)