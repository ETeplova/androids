package com.example.lab1_android.data.api

import com.example.lab1_android.data.model.CharacterDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/v1/public/characters/{id}")
    fun getCharacter(@Path("id") characterId: Int): Call<CharacterDataWrapper>

    @GET("v1/public/characters")
    fun getCharacters(): Call<CharacterDataWrapper>
}