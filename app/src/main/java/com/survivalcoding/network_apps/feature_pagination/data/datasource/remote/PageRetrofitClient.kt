package com.survivalcoding.network_apps.feature_pagination.data.datasource.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PageRetrofitClient {

    fun getClient(): Retrofit {
        val baseUrl = "https://jsonplaceholder.typicode.com"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}