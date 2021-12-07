package com.example.lab1_android.utils

import androidx.room.TypeConverter
import com.example.lab1_android.data.Url
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object UrlListConverter {
    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    @JvmStatic
    fun fromString(value: String) : List<Url>? {
        val type = Types.newParameterizedType(List::class.java, Url::class.java)
        val adapter = moshi.adapter<List<Url>>(type)
        return adapter.fromJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toString(value: List<Url>) : String {
        val type = Types.newParameterizedType(List::class.java, Url::class.java)
        val adapter = moshi.adapter<List<Url>>(type)
        return adapter.toJson(value)
    }
}