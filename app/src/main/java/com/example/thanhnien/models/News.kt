package com.example.thanhnien.models

import com.google.firebase.firestore.Exclude

data class News(
    @Exclude var newsID: String? = "",
    var newsTitle: String = "",
    var newsDesc: String = "",
    var newsGenre: Int = 0,
    var newsImgUrl: String = "",
    var newsCreatedAt: String = ""
)
