package com.survivalcoding.network_apps.feature_paging.domain.repository

import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPosts(): Flow<List<Post>>
}