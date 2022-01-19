package com.survivalcoding.network_apps.feature_paging.data.datasource.network

import com.survivalcoding.network_apps.feature_paging.data.datasource.network.service.JsonService
import com.survivalcoding.network_apps.feature_paging.data.dto.UserEntity

class UserDataSource(private val service: JsonService) {
    suspend fun getUser(id: Int): UserEntity? = service.getUser(id)
}