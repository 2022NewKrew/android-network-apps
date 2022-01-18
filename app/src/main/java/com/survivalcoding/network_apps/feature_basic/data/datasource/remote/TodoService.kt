package com.survivalcoding.network_apps.feature_basic.data.datasource.remote

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import retrofit2.Response
import retrofit2.http.GET

interface TodoService {
    @GET("${Constants.TODO_URL}/{id}")
    suspend fun getTodo(id: Int): Response<Todo>
}