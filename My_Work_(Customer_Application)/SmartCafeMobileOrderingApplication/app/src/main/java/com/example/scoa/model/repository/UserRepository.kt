package com.example.scoa.model.repository

import com.example.scoa.model.database.CartDao
import com.example.scoa.model.database.UserDao
import com.example.scoa.model.entity.CartEntity
import com.example.scoa.model.entity.UserEntity

class UserRepository(
    private val userDao: UserDao,
    private val cartDao: CartDao
) {
    // Save user's profile info and cart to SQLite database
    suspend fun saveUser(user: UserEntity): Long {
        val userId = userDao.insertUser(user)
        cartDao.insertCart(CartEntity(userID = userId.toInt()))
        return userId
    }

    // Retrieve user's local profile using UID
    suspend fun getUserByUid(uid: String): UserEntity? {
        return userDao.getUserByUid(uid)
    }

    // Retrieve user's local profile using local ID
    suspend fun getUserById(id: Int): UserEntity? {
        return userDao.getUserById(id)
    }

    // Get every user
    suspend fun getAllUsers(): List<UserEntity> {
        return userDao.getAllUsers()
    }
}