package com.example.scoa.model.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

// Defines user table structure
@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true) // Autogenerates a unique local ID
    val userID: Int = 0,

    // Stores Firebase UID to associate local profile with Firebase account
    val firebaseUID: String,

    // Customer info
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "delivery_address")
    val deliveryAddress: String
)