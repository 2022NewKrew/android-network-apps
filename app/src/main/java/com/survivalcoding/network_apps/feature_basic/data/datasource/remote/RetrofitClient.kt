package com.survivalcoding.network_apps.feature_basic.data.datasource.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun provideHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private fun getClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TodoApi.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val apiService: TodoApi = getClient(
        provideHttpClient(provideInterceptor())
    ).create(TodoApi::class.java)
}