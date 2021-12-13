package com.example.lab1_android.data.api

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lab1_android.data.dao.SingleCharacterDao
import com.example.lab1_android.data.model.SingleCharacter
import com.example.lab1_android.utils.*

@Database(entities = [SingleCharacter::class], version = 1, exportSchema = false)
@TypeConverters(
    SingleCharacterListConverter::class,
    ThumbnailConverter::class,
    ComicListConverter::class,
    StoryListConverter::class,
    SeriesListConverter::class,
    UrlListConverter::class,
    DateConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun singleCharacterDao(): SingleCharacterDao
}