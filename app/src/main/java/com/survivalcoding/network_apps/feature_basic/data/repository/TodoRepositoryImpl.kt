package com.survivalcoding.network_apps.feature_basic.data.repository

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.repository.BaseRepository
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import com.survivalcoding.network_apps.feature_basic.network.TodosApiService

class TodoRepositoryImpl(
    private val todosApiService: TodosApiService
) : TodoRepository, BaseRepository() {

    override suspend fun getTodoById(id: Int): Todo? {
        return safeApiCall(
            call = { todosApiService.getTodo(id) },
            errorMessage = "Error getting Todo"
        )
    }
}