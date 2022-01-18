package com.survivalcoding.network_apps.feature_basic.data.todo.datasource.local

import com.survivalcoding.network_apps.feature_basic.data.todo.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.domain.model.TodoItem
import javax.inject.Singleton

@Singleton
class LocalTodoDataSource: TodoDataSource {
    override suspend fun getData(id: Int): TodoItem {
        return TodoItem(
            id = 1,
            title = "test",
            completed = true,
            userId = 1
        )
    }
}