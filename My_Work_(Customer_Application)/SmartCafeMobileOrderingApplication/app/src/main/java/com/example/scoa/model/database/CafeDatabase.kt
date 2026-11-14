package com.example.scoa.model.database

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.example.scoa.model.entity.ProductEntity
import com.example.scoa.model.entity.UserEntity
import androidx.room3.ColumnTypeConverters

// Defines database and specifies entities it contains
@Database (
    entities = [UserEntity::class, ProductEntity::class],
    version = 3
)
@ColumnTypeConverters(ProductConverters::class)
abstract class CafeDatabase : RoomDatabase() {
    // Gives access to UserDao operations
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao

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