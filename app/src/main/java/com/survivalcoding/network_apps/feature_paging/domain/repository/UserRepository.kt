package com.survivalcoding.network_apps.feature_paging.domain.repository

import com.survivalcoding.network_apps.feature_paging.domain.model.User

interface UserRepository {
    suspend fun getUserFromNet(id: Int): User?
}