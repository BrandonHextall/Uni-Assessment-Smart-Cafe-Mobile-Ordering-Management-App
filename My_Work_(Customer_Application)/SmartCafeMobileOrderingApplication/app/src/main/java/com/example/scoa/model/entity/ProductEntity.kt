package com.example.scoa.model.entity

import androidx.room3.ColumnInfo
import androidx.room3.ColumnTypeConverter
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("availability")
    val availability: String,
    @ColumnInfo("price")
    val price: Double,
    @ColumnInfo("image")
    val imageName: String,
    @ColumnInfo("category")
    val category: Int,
    @ColumnInfo("allergens")
    val allergens: List<String>
)