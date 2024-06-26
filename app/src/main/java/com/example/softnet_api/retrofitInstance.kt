package com.example.softnet_api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitInstance {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://hunarindia.org.in/") // Base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}