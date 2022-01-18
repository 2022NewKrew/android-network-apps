package com.survivalcoding.network_apps.feature_basic.data.datasource.remote

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoService {
    @GET("${Constants.TODO_URL}/{id}")
    suspend fun getTodo(@Path("id") id: Int): Response<Todo>
}