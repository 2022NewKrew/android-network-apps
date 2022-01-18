package com.survivalcoding.network_apps.feature_basic.data.todo.datasource

import com.survivalcoding.network_apps.feature_basic.domain.model.TodoItem

interface TodoDataSource {
    suspend fun getData(id: Int): TodoItem?
}