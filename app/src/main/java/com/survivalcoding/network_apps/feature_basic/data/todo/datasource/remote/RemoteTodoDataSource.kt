package com.survivalcoding.network_apps.feature_basic.data.todo.datasource.remote

import com.survivalcoding.network_apps.feature_basic.data.todo.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.domain.model.TodoItem
import javax.inject.Inject

class RemoteTodoDataSource @Inject constructor(private val todoService: TodoService) :
    TodoDataSource {
    override suspend fun getData(id: Int): TodoItem? {
        val response = todoService.getTodo(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}