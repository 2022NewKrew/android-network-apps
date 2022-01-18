package com.survivalcoding.network_apps.feature_basic.data.repository

import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.TodoRemoteDataSource
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val localDataSource: TodoRemoteDataSource
) : TodoRepository {

    override suspend fun getTodoById(id: Int): Result<Todo> {
        return localDataSource.getData(id)
    }
}