package com.survivalcoding.network_apps.feature_basic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object TodosApi {
    val retrofitService: TodosApiService by lazy {
        retrofit.create(TodosApiService::class.java)
    }
}