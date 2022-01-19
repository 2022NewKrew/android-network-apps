package com.survivalcoding.network_apps.feature_paging.data.datasource

import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostDataSource {
    suspend fun getPosts(): Flow<List<Post>>
}