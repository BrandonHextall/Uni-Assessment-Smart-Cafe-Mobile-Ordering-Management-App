package com.example.scoa.model.database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import com.example.scoa.model.entity.UserEntity

// Defines database operations
@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("""
        SELECT * FROM usrers WHERE firebaseUID = :uid
        LIMIT 1
    """)
    suspend fun getUser(uid: String): UserEntity?
}