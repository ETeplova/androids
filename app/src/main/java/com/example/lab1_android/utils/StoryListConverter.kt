package com.example.lab1_android.utils

import androidx.room.TypeConverter
import com.example.lab1_android.data.StoryList
import com.squareup.moshi.Moshi

object StoryListConverter {
    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    @JvmStatic
    fun fromString(value: String) : StoryList? {
        return moshi.adapter(StoryList::class.java).fromJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toString(value: StoryList) : String {
        return moshi.adapter(StoryList::class.java).toJson(value)
    }
}