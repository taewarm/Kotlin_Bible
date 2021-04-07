package com.example.kotlin_bible

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TWAPIConfig {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl("http://13.124.172.29:1750/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    fun getService(): TWAPI = retrofit.create(TWAPI::class.java)
}