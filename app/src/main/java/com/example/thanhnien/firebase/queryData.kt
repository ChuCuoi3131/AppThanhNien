package com.example.thanhnien.firebase

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import com.example.thanhnien.models.Genre
import com.example.thanhnien.models.News
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

suspend fun getNewsFromFirebase(genreID: Int): List<News> {
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val querySnapshot = db.collection("News")
//        .whereEqualTo("newsGenre", genreID)
        .get().await()
    val newsList: MutableList<News> = mutableListOf()
    for (document in querySnapshot.documents) {
        val news = document.toObject(News::class.java)
        news?.newsID = document.id
        news?.let { newsList.add(it) }
    }
    return newsList
}

suspend fun getGenreFromFirebase(): List<Genre> {
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val querySnapshot = db.collection("Genre").get().await()
    val genreList: MutableList<Genre> = mutableListOf()
    for (document in querySnapshot.documents) {
        val genre = document.toObject(Genre::class.java)
        genre?.genreID = document.id
        genre?.let { genreList.add(it) }
    }
    return genreList
}