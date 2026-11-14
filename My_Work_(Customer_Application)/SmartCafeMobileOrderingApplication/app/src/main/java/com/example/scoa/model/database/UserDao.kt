package com.example.scoa.model.database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import com.example.scoa.model.entity.UserEntity

// @Dao marks this interface as a Data Access Object
@Dao
// Defines database operations
interface UserDao {
    @Insert
    // Suspend functions can pause execution while waiting for something else to finish
    suspend fun insertUser(user: UserEntity)
    // Retrieves a customer with their UID
    // Null if no matching customer exists
    @Query("""
        SELECT * FROM users WHERE firebaseUID = :uid
        LIMIT 1
    """)
    suspend fun getUser(uid: String): UserEntity?

    // Retrieves every customer record
    @Query("SELECT * FROM USERS")
    suspend fun getAllUsers(): List<UserEntity>
}