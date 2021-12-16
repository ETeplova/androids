package com.example.lab1_android.data.repository

import androidx.annotation.WorkerThread
import com.example.lab1_android.data.model.SingleCharacter
import com.example.lab1_android.data.dao.SingleCharacterDao
import javax.inject.Inject

class SingleCharacterRepository@Inject constructor(private val singleCharacterDao: SingleCharacterDao) :
    ISingleCharacterRepository {
    val characterList: List<SingleCharacter> = singleCharacterDao.getCharacterList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertList(characterList: List<SingleCharacter>) {
        for (ch in characterList) {
            singleCharacterDao.insert(ch)
        }
    }
}