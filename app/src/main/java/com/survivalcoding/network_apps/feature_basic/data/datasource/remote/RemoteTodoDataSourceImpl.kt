package com.survivalcoding.network_apps.feature_basic.data.datasource.remote

import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import javax.inject.Inject

class RemoteTodoDataSourceImpl @Inject constructor(private val todoService: TodoService) :
    TodoDataSource {
    override suspend fun getData(id: Int): Todo? {
        val response = todoService.getTodo(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}