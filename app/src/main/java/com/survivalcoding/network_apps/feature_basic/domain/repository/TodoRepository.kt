package com.survivalcoding.network_apps.feature_basic.domain.repository

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo

interface TodoRepository {
    suspend fun getTodoById(id: Int): Todo
}