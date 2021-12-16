package com.example.lab1_android.utils

import androidx.room.TypeConverter
import com.example.lab1_android.data.model.SingleCharacter
import com.squareup.moshi.Moshi

object SingleCharacterListConverter {

    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    @JvmStatic
    fun fromString(value: String) : SingleCharacter? {
        return moshi.adapter(SingleCharacter::class.java).fromJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toString(value: SingleCharacter) : String {
        return moshi.adapter(SingleCharacter::class.java).toJson(value)
    }
}