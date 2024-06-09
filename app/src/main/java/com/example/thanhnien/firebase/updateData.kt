package com.example.thanhnien.firebase

import android.content.Context
import android.widget.Toast
import com.example.thanhnien.models.Profile
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
suspend fun updateProfileToFirebase(
    id: String,
    email: String,
    name: String,
    dob: String,
    gender: Int,
    joinedAt: String,
    role: String,
    act: String,
    ethnicity: String,
    religion: String,
    context: Context
): Boolean {
    var flag = false
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val profile = Profile(id, email, name, dob, gender, joinedAt, role, act, ethnicity, religion)
    db.collection("Profile").document(id).set(profile)
        .addOnSuccessListener {
            flag = true
            Toast.makeText(context, "Cập nhật thành công!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(context, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show()
        }
    return flag
}