package com.survivalcoding.network_apps.feature_basic.data.datasource.remote

import javax.inject.Inject

class RemoteTodoDataSource @Inject constructor(private val todoService: TodoService) {
    suspend fun getTodo(id: Int) = todoService.getTodo(id)
}