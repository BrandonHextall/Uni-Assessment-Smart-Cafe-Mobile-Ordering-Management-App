package com.example.scoa.model

import com.google.firebase.firestore.FirebaseFirestore

class UserRepository (
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    fun saveUser (
        user: User,
        onResult: (Result<Unit>) -> Unit
    ) {
        db.collection("Users")
            .document(user.userID)
            .set(user)
            .addOnSuccessListener {
                onResult(Result.success(Unit))
            }
            .addOnFailureListener { exception ->
                onResult(Result.failure(exception))
            }
    }
}