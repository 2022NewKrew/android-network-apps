package com.survivalcoding.network_apps.feature_basic.data.repository

import com.survivalcoding.network_apps.feature_basic.data.datasource.local.LocalTodoDataSource
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import kotlinx.coroutines.delay

class TodoRepositoryImpl(
    private val dataSource: LocalTodoDataSource
) : TodoRepository {

    override suspend fun getTodoById(id: Int): Todo {
        delay(1000)
        return dataSource.getData()
    }
}