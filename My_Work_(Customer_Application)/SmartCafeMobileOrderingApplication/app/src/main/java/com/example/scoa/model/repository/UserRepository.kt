package com.example.scoa.model.repository

import com.example.scoa.model.database.UserDao
import com.example.scoa.model.entity.UserEntity

class UserRepository (
    private val userDao: UserDao,
) {
    // Save user's profile info to SQLite database
    suspend fun saveUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    // Retrieve user's local profile using UID
    suspend fun getUser(uid: String): UserEntity? {
        return userDao.getUser(uid)
    }

    // Get every user
    suspend fun getAllUsers(): List<UserEntity> {
        return userDao.getAllUsers()
    }
}