package com.survivalcoding.network_apps.feature_basic.data.datasource.remote

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo

class TodoRemoteDataSource(
    private val api: TodoApi
) {
    suspend fun getData(id: Int): Todo {
        return api.getTodo(id)
    }
}