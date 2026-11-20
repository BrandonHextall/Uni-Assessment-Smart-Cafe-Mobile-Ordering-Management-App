package com.example.scoa.model.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "orderItems")
data class OrderItemEntity(
    @PrimaryKey(autoGenerate = true)
    val orderItemID: Int = 0,
)