package com.example.thanhnien.firebase

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import com.example.thanhnien.models.Genre
import com.example.thanhnien.models.News
import com.example.thanhnien.models.Profile
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.tasks.await

@DelicateCoroutinesApi
suspend fun checkLoginAuthentication(email: String, password: String, context: Context): String {
//    var flag = false
    var fullName: String = ""
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    try {
        val querySnapshot = db.collection("Account")
            .whereEqualTo("email", email)
            .whereEqualTo("password", password)
            .get()
            .await()

        if (!querySnapshot.isEmpty) {
//            flag = true
            val document = querySnapshot.documents.firstOrNull()
            val accountData = document?.data
            val fullname = accountData?.get("fullName") as? String
            if (fullname != null) {
                fullName = fullname
            }
            Toast.makeText(context, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Đã xảy ra lỗi! Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show()
    }
    return fullName
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

@DelicateCoroutinesApi
suspend fun getProfileFromFirebaseByEmail(email: String, name: String): Profile {
    var profile = Profile("", email, name, "", 0, "", "", "", "", "")
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    try {
        val querySnapshot = db.collection("Profile")
            .whereEqualTo("profileEmail", email)
            .get()
            .await()

        if (!querySnapshot.isEmpty) {
            val document = querySnapshot.documents.firstOrNull()
            val id = document?.id
            val accountData = document?.data
            val dob = accountData?.get("profileDob") as? String
            var gender = accountData?.get("profileGender") as? Int
            if (gender == null) gender = 0 else gender = 1
            val joinedAt = accountData?.get("profileJoinedAt") as? String
            val role = accountData?.get("profileRole") as? String
            val act = accountData?.get("profileACT") as? String
            val ethnicity = accountData?.get("profileEthnicity") as? String
            val religion = accountData?.get("profileReligion") as? String
            profile = Profile(id, email, name, dob, gender, joinedAt, role, act, ethnicity, religion)
        }
    } catch (e: Exception) {}
    return profile
}