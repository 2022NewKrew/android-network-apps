package com.survivalcoding.network_apps.feature_basic.data.datasource.remote

import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.service.TodoService
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.model.emptyTodo

class RemoteTodoDataSource : TodoDataSource {
    override suspend fun getTodoById(id: Int): Todo =
        RetrofitClient.getClient().create(TodoService::class.java).getTodoById(id).body()
            ?: emptyTodo()
}