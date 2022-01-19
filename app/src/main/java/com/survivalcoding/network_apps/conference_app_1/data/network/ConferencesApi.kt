package com.survivalcoding.network_apps.conference_app_1.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://raw.githubusercontent.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object ConferencesApi {
    val retrofitService: ConferencesApiService by lazy {
        retrofit.create(ConferencesApiService::class.java)
    }
}