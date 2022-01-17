package com.survivalcoding.network_apps.feature_basic.data.network

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface TodosApiService {
    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") id: Int): Response<Todo>
}