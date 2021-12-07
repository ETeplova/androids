package com.example.lab1_android.data.api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lab1_android.data.SingleCharacter
import com.example.lab1_android.data.dao.SingleCharacterDao
import com.example.lab1_android.utils.*

@Database(entities = [SingleCharacter::class], version = 1, exportSchema = false)
@TypeConverters(
    SingleCharacterListConverter::class, ThumbnailConverter::class, ComicListConverter::class,
    StoryListConverter::class, SeriesListConverter::class, UrlListConverter::class, DateConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun singleCharacterDao(): SingleCharacterDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "myDB"
                    )
                        .allowMainThreadQueries()  // ОЧЕНЬ ОЧЕНЬ ПЛОХО! потом мы будем инжектить ее и это не пригодится
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}