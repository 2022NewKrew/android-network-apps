package com.survivalcoding.network_apps.feature_basic.data.repository

import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.TodoService
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRemoteRepository @Inject constructor(private val todoService: TodoService): TodoRepository {
    override suspend fun getTodoById(id: Int): Todo {
        return todoService.getTodoById(id)
    }
}