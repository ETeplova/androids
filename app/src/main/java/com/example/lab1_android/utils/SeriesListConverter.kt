package com.example.lab1_android.utils

import androidx.room.TypeConverter
import com.example.lab1_android.data.SeriesList
import com.squareup.moshi.Moshi

object SeriesListConverter {
    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    @JvmStatic
    fun fromString(value: String) : SeriesList? {
        return moshi.adapter(SeriesList::class.java).fromJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toString(value: SeriesList) : String {
        return moshi.adapter(SeriesList::class.java).toJson(value)
    }
}