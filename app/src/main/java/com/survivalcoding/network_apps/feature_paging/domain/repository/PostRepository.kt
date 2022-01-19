package com.survivalcoding.network_apps.feature_paging.domain.repository

import com.survivalcoding.network_apps.feature_paging.domain.model.Post

interface PostRepository {
    suspend fun getPosts(page: Int): List<Post>
}