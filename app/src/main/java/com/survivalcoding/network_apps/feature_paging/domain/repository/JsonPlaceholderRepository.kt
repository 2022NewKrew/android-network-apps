package com.survivalcoding.network_apps.feature_paging.domain.repository

import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User

interface JsonPlaceholderRepository {
    suspend fun getPosts(page: Int, pageSize: Int): List<Post>
    suspend fun getUser(id: Int): User
}