package com.example.lab1_android.data.api

import com.example.lab1_android.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {
    private val timestamp: Long = System.currentTimeMillis()

    private var interceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("ts", timestamp.toString())
            .addQueryParameter("apikey", "0b26845ab0185ac5991192bfb9e553a8")
            .addQueryParameter("hash", ApiRequestEncryption.toMD5hash(timestamp.toString()))
            .build()

        val newRequest = chain.request().newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    val moshi = Moshi.Builder().build()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    fun api() : ApiInterface {
        return retrofit().create(ApiInterface::class.java)
    }
}