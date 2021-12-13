package com.example.lab1_android.di

import android.content.Context
import androidx.room.Room
import com.example.lab1_android.data.api.ApiInterface
import com.example.lab1_android.data.api.AppDatabase
import com.example.lab1_android.data.api.RetrofitFactory
import com.example.lab1_android.data.dao.SingleCharacterDao
import com.example.lab1_android.data.repository.ISingleCharacterRepository
import com.example.lab1_android.data.repository.SingleCharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "myDB"
    ).allowMainThreadQueries()
        .build()

    @Provides
    fun provideApi(): ApiInterface = RetrofitFactory.api()

    @Provides
    fun provideDao(db: AppDatabase) = db.singleCharacterDao()

    @Provides
    fun provideRepository(dao: SingleCharacterDao): ISingleCharacterRepository =
        SingleCharacterRepository(dao)
}