package com.survivalcoding.network_apps.feature_basic.domain.repository

import com.survivalcoding.network_apps.feature_basic.domain.model.TodoItem

interface TodoRepository {
    suspend fun getTodoById(id: Int): TodoItem?
}