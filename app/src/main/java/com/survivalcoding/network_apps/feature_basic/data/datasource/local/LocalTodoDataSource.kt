package com.survivalcoding.network_apps.feature_basic.data.datasource.local

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo

class LocalTodoDataSource {

    suspend fun getData(): Todo {
        return Todo(
            id = 1,
            title = "test",
            completed = true,
            userId = 1
        )
    }
}