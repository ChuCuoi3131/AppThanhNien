package com.example.thanhnien.models

import com.google.firebase.firestore.Exclude

data class Account(
    @Exclude var email: String,
    var fullName: String,
    var password: String,
    var permission: Int,
    var createdAt: String
)
