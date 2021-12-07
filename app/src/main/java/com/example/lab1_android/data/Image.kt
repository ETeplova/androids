package com.example.lab1_android.data

import androidx.room.Entity

@Entity(tableName = "image")
data class Image(
    var path: String? = null,
    var extension: String? = null
) {
}