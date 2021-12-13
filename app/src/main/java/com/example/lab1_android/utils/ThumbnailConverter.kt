package com.example.lab1_android.utils

import androidx.room.TypeConverter
import com.example.lab1_android.data.model.Image
import com.squareup.moshi.Moshi

object ThumbnailConverter {
    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    @JvmStatic
    fun fromString(value: String) : Image? {
        return moshi.adapter(Image::class.java).fromJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toString(value: Image) : String {
        return moshi.adapter(Image::class.java).toJson(value)
    }
}