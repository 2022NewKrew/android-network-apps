package com.survivalcoding.network_apps.feature_basic.data.datasource.local

import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import javax.inject.Singleton

@Singleton
class LocalTodoDataSourceImpl: TodoDataSource {
    override suspend fun getData(id: Int): Todo {
        return Todo(
            id = 1,
            title = "test",
            completed = true,
            userId = 1
        )
    }
}