package com.survivalcoding.network_apps.feature_basic.data.repository

import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.domain.model.TodoItem
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl @Inject constructor(
    private val dataSource: TodoDataSource
) : TodoRepository {

    override suspend fun getTodoById(id: Int): TodoItem? {
        return dataSource.getData(id)
    }
}