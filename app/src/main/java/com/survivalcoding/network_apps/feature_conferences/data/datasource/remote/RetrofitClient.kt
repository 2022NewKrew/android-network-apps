package com.survivalcoding.network_apps.feature_conferences.data.datasource.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val TIMEOUT_COUNT: Long = 10

    fun getClient(): Retrofit {
        val baseUrl = "https://raw.githubusercontent.com/junsuk5≈"

        val client = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_COUNT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_COUNT, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}