package com.survivalcoding.network_apps.feature_basic.data.datasource.remote.service

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import retrofit2.http.GET
import retrofit2.http.Path

interface BasicTodoService {
    @GET("/todos/{id}")
    suspend fun getTodo(@Path("id") id: Int): Todo

    @GET("/todos")
    suspend fun getAllTodos(): ArrayList<Todo>

    companion object {
        const val BASIC_BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}