package com.example.thanhnien.firebase

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.tasks.await

@DelicateCoroutinesApi
suspend fun checkLoginAuthentication(email: String, password: String, context: Context): Boolean {
    var flag = false
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    try {
        val querySnapshot = db.collection("Account")
            .whereEqualTo("email", email)
            .whereEqualTo("password", password)
            .get()
            .await()

        if (!querySnapshot.isEmpty) {
            flag = true
            Toast.makeText(context, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Đã xảy ra lỗi! Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show()
    }
    return flag
}