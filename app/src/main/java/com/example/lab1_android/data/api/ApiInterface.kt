package com.example.lab1_android.data.api

import com.example.lab1_android.data.CharacterDataWrapper
import com.example.lab1_android.data.SingleCharacter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Response

interface ApiInterface {
    @GET("/v1/public/characters/{id}")
    //@GET("v1/characters/{id}")
    fun getCharacter(@Path("id") characterId: Int): Call<CharacterDataWrapper>

    @GET("v1/public/characters")
    fun getCharacters() : Call<CharacterDataWrapper>
}