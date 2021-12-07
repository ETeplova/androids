package com.example.lab1_android.utils

import androidx.room.TypeConverter
import com.example.lab1_android.data.Date
import com.squareup.moshi.Moshi

object DateConverter {
    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    @JvmStatic
    fun fromString(value: String) : Date? {
        return moshi.adapter(Date::class.java).fromJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toString(value: Date) : String {
        return moshi.adapter(Date::class.java).toJson(value)
    }
}