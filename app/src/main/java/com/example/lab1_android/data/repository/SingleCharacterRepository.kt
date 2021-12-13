package com.example.lab1_android.data.repository

import androidx.annotation.WorkerThread
import com.example.lab1_android.data.SingleCharacter
import com.example.lab1_android.data.dao.SingleCharacterDao

class SingleCharacterRepository(private val singleCharacterDao: SingleCharacterDao) {
    val characterList: List<SingleCharacter> = singleCharacterDao.getCharacterList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertList(characterList: List<SingleCharacter>) {
        for (ch in characterList) {
            singleCharacterDao.insert(ch)
        }
    }
}