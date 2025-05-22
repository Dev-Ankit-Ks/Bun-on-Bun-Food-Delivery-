package com.example.bunonbun.API

import android.telecom.Call
import com.example.bunonbun.Models.Quotes
import com.example.bunonbun.OrderRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {
    @GET("random")
    fun getData():retrofit2.Call<List<Quotes>>
    @POST("api/order")
    fun sendOrder(@Body orderRequest: OrderRequest):retrofit2.Call<Void>
}