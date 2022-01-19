package com.survivalcoding.network_apps.feature_basic.data.datasource.remote

import com.survivalcoding.network_apps.feature_basic.core.Result
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo

class TodoRemoteDataSource(
    private val api: TodoApi
) {
    suspend fun getData(id: Int): Result<Todo> {
        return try {
            Result.Success(api.getTodo(id))
        } catch (e: Exception) {
            Result.Error("TodoApi Error", e)
        }
    }
}