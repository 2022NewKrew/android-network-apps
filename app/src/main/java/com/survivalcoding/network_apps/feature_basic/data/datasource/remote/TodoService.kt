package com.survivalcoding.network_apps.feature_basic.data.datasource.remote

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoService {

    @GET("todos/{id}")
    suspend fun getTodoById(@Path("id") id: Int): Todo
}