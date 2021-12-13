package com.example.lab1_android.data.dao

import androidx.room.*
import com.example.lab1_android.data.model.SingleCharacter

@Dao
interface SingleCharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(singleCharacter: SingleCharacter): Long?

    @Update
    suspend fun update(singleCharacter: SingleCharacter)

    @Delete
    suspend fun delete(singleCharacter: SingleCharacter)

    @Query("SELECT * from singleCharacter")
    fun getCharacterList(): List<SingleCharacter>
}