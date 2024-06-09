package com.example.thanhnien.models

import com.google.firebase.firestore.Exclude

data class Profile(
    @Exclude var profileID: String? = "",
    var profileEmail: String = "",
    var profileName: String = "",
    var profileDob: String? = "",
    var profileGender: Int = 0,
    var profileJoinedAt: String? = "",
    var profileRole: String? = "",
    var profileACT: String? = "",
    var profileEthnicity: String? = "",
    var profileReligion: String? = ""
)
