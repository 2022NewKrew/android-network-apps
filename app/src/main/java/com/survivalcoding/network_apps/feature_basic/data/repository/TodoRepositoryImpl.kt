package com.survivalcoding.network_apps.feature_basic.data.repository

import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val dataSource: TodoDataSource
) : TodoRepository {
    override suspend fun getTodoById(id: Int): Todo {
        return dataSource.getTodoById(id)
    }
}