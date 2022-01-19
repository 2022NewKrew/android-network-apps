package com.survivalcoding.network_apps.feature_paging.data.repository

import com.survivalcoding.network_apps.feature_paging.data.datasource.PostDataSource
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(private val dataSource: PostDataSource): PostRepository {
    override suspend fun getPosts(): Flow<List<Post>> = dataSource.getPosts()
}