package com.survivalcoding.network_apps.feature_paging.data.repositoryimpl

import com.survivalcoding.network_apps.feature_paging.data.UserMapper
import com.survivalcoding.network_apps.feature_paging.data.datasource.network.UserDataSource
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import com.survivalcoding.network_apps.feature_paging.domain.repository.UserRepository
import java.lang.Exception
import java.security.spec.ECField

class UserRepositoryImpl(private val userDataSource: UserDataSource) : UserRepository {

    override val users: MutableMap<Int, User?> = mutableMapOf()

    // 임시로 에러 처리 전부 흡수
    override suspend fun getUserFromNet(id: Int): User? = try {
        userDataSource.getUser(id)?.let {
            val model = UserMapper.toModel(it)
            users[id] = model
            model
        }
    } catch (e: Exception) {
        null
    }

    override fun getUserFromCache(id: Int): User? = users[id]

}