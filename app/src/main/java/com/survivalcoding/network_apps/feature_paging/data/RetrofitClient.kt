package com.survivalcoding.network_apps.feature_paging.data

import com.survivalcoding.network_apps.feature_paging.data.data_source.PhotoApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(PhotoApi.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: PhotoApi = getClient().create(PhotoApi::class.java)
}