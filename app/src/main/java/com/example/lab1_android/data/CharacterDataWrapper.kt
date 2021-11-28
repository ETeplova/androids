package com.example.lab1_android.data

data class CharacterDataWrapper(
    var code: Int? = null,
    var status: String? = null,
    var copyright: String? = null,
    var attributionText: String? = null,
    var attributionHTML: String? = null,
    var data: CharacterDataContainer? = null,
    var etag: String? = null
)
