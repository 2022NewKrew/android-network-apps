package com.survivalcoding.network_apps.feature_basic.network

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface TodosApiService {
    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") id: Int): Response<Todo>
}

object TodosApi {
    val retrofitService: TodosApiService by lazy {
        retrofit.create(TodosApiService::class.java)
    }
}