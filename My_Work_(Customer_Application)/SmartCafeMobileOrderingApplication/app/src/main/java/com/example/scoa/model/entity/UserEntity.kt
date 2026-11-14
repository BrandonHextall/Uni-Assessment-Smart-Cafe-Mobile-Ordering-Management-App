package com.example.scoa.model.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

// Defines customer table structure
@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val userID: Int = 0,

    val firebaseUID: String,
    val fullName: String,
    val email: String,
    val deliveryAddress: String
)