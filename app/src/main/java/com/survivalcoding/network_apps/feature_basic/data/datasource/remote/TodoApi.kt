package com.survivalcoding.network_apps.feature_basic.data.datasource.remote

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoApi {

    @GET("$baseUrl/todos/{id}")
    suspend fun getTodo(
        @Path("id") id: Int
    ): Todo

    companion object {
        const val baseUrl = "https://jsonplaceholder.typicode.com"
    }
}