package com.survivalcoding.network_apps.feature_paging.domain.repository

import com.survivalcoding.network_apps.feature_paging.domain.model.User

interface UserRepository {
    val users: MutableMap<Int, User?>
    suspend fun getUserFromNet(id: Int): User?
    fun getUserFromCache(id: Int): User?
}