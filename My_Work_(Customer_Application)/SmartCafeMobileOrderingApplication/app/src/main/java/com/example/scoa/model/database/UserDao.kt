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
    suspend fun insertUser(user: UserEntity): Long

    // Retrieves a customer with their UID
    // Null if no matching customer exists
    @Query("""
        SELECT * FROM users WHERE firebaseUID = :uid
        LIMIT 1
    """)
    suspend fun getUserByUid(uid: String): UserEntity?

    // Retrieves customer by their local ID
    // Null if no matching customer exists
    @Query("""
        SELECT * FROM users WHERE userID = :id
        LIMIT 1
    """)
    suspend fun getUserById(id: Int): UserEntity?

    // Retrieves every customer record
    @Query("SELECT * FROM USERS")
    suspend fun getAllUsers(): List<UserEntity>
}