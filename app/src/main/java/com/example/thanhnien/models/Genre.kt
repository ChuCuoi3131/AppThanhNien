package com.example.thanhnien.models

import com.google.firebase.firestore.Exclude

data class Genre(
    @Exclude var genreID: String? = "",
    var genreName: String = ""
)
