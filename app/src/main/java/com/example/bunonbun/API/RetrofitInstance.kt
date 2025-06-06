package com.example.bunonbun.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy{

        Retrofit.Builder().baseUrl("https://api.quotable.io/quotes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiInterface by lazy{
        retrofit.create(ApiInterface::class.java)
    }
}