package com.example.lab1_android.data.repository

import com.example.lab1_android.data.model.SingleCharacter

// вобщем суть вот этой штуки.. казалось нафига эти се интерфейсы.
// а если мы захотим вместо этого репозитория использовтаь тестовый?
// так вот мы береи и имплементируемся от интерфейса и реализуем все эти функции уже для другого тестового репозитория
// что касается названий - надо бы SingleCharacterRepository (интерфейс) и SingleCharacterRepositoryImplemntation
// но мне начинать с I интерфейсы приятнее
interface ISingleCharacterRepository {
    suspend fun insertList(characterList: List<SingleCharacter>)
}