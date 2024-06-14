package com.example.fetchtakehometest.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Retrofit needs the base url and converter which will be provided in the builder
private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

// Retrofit builder
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create()) // Converter provided
    .baseUrl(BASE_URL)
    .build()

// Implementing the ItemsApiService interface with the @GET getItemsProperties returning a String
interface ItemsApiService {
    @GET("hiring.json")
    suspend fun getItemsProperties(): Response<List<Item>>
}


// Creating the ItemsApi object using Retrofit to implement the ItemsApiService
object ItemsApi {
    val retrofitService : ItemsApiService by lazy {
        retrofit.create(ItemsApiService::class.java)
    }
}