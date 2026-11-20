package com.example.scoa.model.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey

// Defines user table structure
@Entity(
    tableName = "users",
    indices = [
        Index(value = ["firebaseUID"], unique = true),
        Index(value = ["email"], unique = true)
    ]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) // Autogenerates a unique local ID
    val userID: Int = 0,

    // Stores Firebase UID to associate local profile with Firebase account
    @ColumnInfo(name = "firebaseUID")
    val firebaseUID: String,

    // Customer info
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "delivery_address")
    val deliveryAddress: String
)