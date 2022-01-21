package com.survivalcoding.network_apps.feature_pagination.data.datasource.remote

import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.model.User

interface PageRemoteDataSource {
    suspend fun getUsers(): List<User>
    suspend fun getPosts(): List<Post>
}