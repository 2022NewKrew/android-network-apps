package com.survivalcoding.network_apps.feature_paging.data.datasource.remote

import com.survivalcoding.network_apps.feature_paging.data.datasource.PostDataSource
import com.survivalcoding.network_apps.feature_paging.data.datasource.remote.service.PostService
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RemotePostDataSource : PostDataSource {
    override suspend fun getPosts(): Flow<List<Post>> =
        flowOf(
            RetrofitClient.getClient().create(PostService::class.java).getPosts()
        )
}