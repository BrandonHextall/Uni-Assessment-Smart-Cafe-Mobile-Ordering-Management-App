
package com.example.scoa.model.database

import androidx.room3.ColumnTypeConverter

// Converts products to a saveable data type
class ProductConverters {

    // Converts an ArrayList of strings into one String for SQLite storage.
    // Example: ["Milk", "Eggs", "Wheat"] becomes "Milk,Eggs,Wheat".
    @ColumnTypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(",")
    }

    // Converts the stored String back into a List of strings.
    // Example: "Milk,Eggs,Wheat" becomes ["Milk", "Eggs", "Wheat"].
    @ColumnTypeConverter
    fun toStringList(value: String): List<String> {
        return if (value.isEmpty()) {
            emptyList()
        } else {
            value.split(",")
        }
    }
}
