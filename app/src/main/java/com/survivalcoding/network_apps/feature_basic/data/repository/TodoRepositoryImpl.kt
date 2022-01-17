package com.survivalcoding.network_apps.feature_basic.data.repository

import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.BasicTodoNetworkSource
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val dataSource: BasicTodoNetworkSource
) : TodoRepository {

    override suspend fun getTodoById(id: Int): Todo {
        return dataSource.getTodo(id)
    }

    override suspend fun getAllTodo(): List<Todo> {
        return dataSource.getAllTodos()
    }
}