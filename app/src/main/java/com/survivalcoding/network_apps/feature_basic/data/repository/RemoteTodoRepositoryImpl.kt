package com.survivalcoding.network_apps.feature_basic.data.repository

import com.survivalcoding.network_apps.feature_basic.data.datasource.network.RemoteDataSource
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository

class RemoteTodoRepositoryImpl(private val remoteDataSource: RemoteDataSource) : TodoRepository {

    override suspend fun getTodoById(id: Int): Todo = remoteDataSource.getTodo(id)
}