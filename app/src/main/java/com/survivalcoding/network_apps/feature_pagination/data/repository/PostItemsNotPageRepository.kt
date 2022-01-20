package com.survivalcoding.network_apps.feature_pagination.data.repository

import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.PageRemoteDataSource
import com.survivalcoding.network_apps.feature_pagination.domain.model.Post

class PostItemsNotPageRepository(
    private val dataSource: PageRemoteDataSource
) {
    suspend fun getPostItems(): List<Post> {
        return dataSource.getPosts()
    }
}