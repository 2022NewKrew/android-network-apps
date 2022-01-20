package com.survivalcoding.network_apps.feature_paging.data.repositoryimpl

import com.survivalcoding.network_apps.feature_paging.data.UserMapper
import com.survivalcoding.network_apps.feature_paging.data.datasource.network.UserDataSource
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import com.survivalcoding.network_apps.feature_paging.domain.repository.UserRepository

class UserRepositoryImpl(private val userDataSource: UserDataSource) : UserRepository {
    override suspend fun getUser(id: Int): User? = userDataSource.getUser(id)?.let {
        UserMapper.toModel(it)
    }

}