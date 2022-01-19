package com.survivalcoding.network_apps.feature_basic.data.datasource.network.service

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import retrofit2.http.GET
import retrofit2.http.Path

interface SampleJsonService {

    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") id: Int): Todo
}