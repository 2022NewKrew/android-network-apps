package com.survivalcoding.network_apps.feature_basic.data.datasource.remote

import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.service.BasicTodoService
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.model.TodoContainer

class BasicTodoNetworkSource(
    private val todoService: BasicTodoService,
) {
    suspend fun getTodo(id: Int): Todo {
        return todoService.getTodo(id)
    }

    suspend fun getAllTodos(): TodoContainer {
        return todoService.getAllTodos()
    }
}