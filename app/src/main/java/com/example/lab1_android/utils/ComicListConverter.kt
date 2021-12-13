package com.example.lab1_android.utils

import androidx.room.TypeConverter
import com.example.lab1_android.data.ComicList
import com.squareup.moshi.Moshi

object ComicListConverter {
    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    @JvmStatic
    fun fromString(value: String) : ComicList? {
        return moshi.adapter(ComicList::class.java).fromJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toComicList(value: ComicList) : String {
        return moshi.adapter(ComicList::class.java).toJson(value)
    }
}