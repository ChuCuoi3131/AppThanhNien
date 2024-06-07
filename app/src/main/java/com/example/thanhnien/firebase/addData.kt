package com.example.thanhnien.firebase

import android.content.Context
import android.widget.Toast
import com.example.thanhnien.models.Account
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

fun addAccountToFirebase(
    phone: String,
    fullName: String,
    password: String,
    permission: Int,
    createdAt: String,
    context: Context
) {
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val dbAccount: CollectionReference = db.collection("Account")
    val account = Account(phone, fullName, password, permission, createdAt)

    dbAccount.add(account).addOnSuccessListener {
        Toast.makeText(context, "Đăng kí thành công", Toast.LENGTH_SHORT).show()
    }.addOnFailureListener {
        Toast.makeText(context, "Đăng kí thất bại", Toast.LENGTH_SHORT).show()
    }
}