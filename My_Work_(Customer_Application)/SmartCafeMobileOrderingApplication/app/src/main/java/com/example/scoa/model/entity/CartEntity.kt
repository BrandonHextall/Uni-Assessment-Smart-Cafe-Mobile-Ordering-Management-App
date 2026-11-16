package com.example.scoa.model.entity

import androidx.room3.*

@Entity(
    tableName = "carts",
    foreignKeys = [
        ForeignKey (
            entity = UserEntity::class,
            parentColumns = ["userID"],
            childColumns = ["userID"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userID"], unique = true)]
)
data class CartEntity (
    @PrimaryKey(autoGenerate = true)
    val cartID: Int = 0,
    @ColumnInfo("userID")
    val userID: Int,
)