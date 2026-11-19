package com.example.scoa.model.database

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.example.scoa.model.entity.ProductEntity
import com.example.scoa.model.entity.UserEntity
import androidx.room3.ColumnTypeConverters
import com.example.scoa.model.entity.CartEntity
import com.example.scoa.model.entity.CartItemEntity
import com.example.scoa.model.entity.OrderEntity
import com.example.scoa.model.entity.OrderItemEntity

// Defines database and specifies entities it contains
@Database (
    entities = [
        UserEntity::class,
        ProductEntity::class,
        CartEntity::class,
        CartItemEntity::class,
        OrderEntity::class,
        OrderItemEntity::class
    ],
    version = 8
)
@ColumnTypeConverters(ProductConverters::class)
abstract class CafeDatabase : RoomDatabase() {
    // Gives access to UserDao operations
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao

    companion object {
        // Holds the only database instance shared through the app
        @Volatile
        private var INSTANCE: CafeDatabase? = null

        // Returns existing database instance
        // or creates one if it's the first time accessing the database
        fun getDatabase(context: Context): CafeDatabase {
            return INSTANCE ?: synchronized(this) {
                // Creates SQLite database using application context
                val db = Room.databaseBuilder (
                    context.applicationContext,
                    CafeDatabase::class.java,
                    "cafe_database"
                ) .fallbackToDestructiveMigration(true)
                    .build()

                // Save instance for reuse
                INSTANCE = db
                db
            }
        }
    }
}