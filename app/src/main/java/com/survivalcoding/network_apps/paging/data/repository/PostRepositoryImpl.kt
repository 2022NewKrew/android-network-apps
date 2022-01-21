package com.survivalcoding.network_apps.paging.data.repository

import com.survivalcoding.network_apps.paging.data.datasource.remote.PostRemoteDataSource
import com.survivalcoding.network_apps.paging.domain.repository.PostRepository

class PostRepositoryImpl(
    private val postRemoteDataSource: PostRemoteDataSource
) : PostRepository {
    override fun getResultStream() = postRemoteDataSource.getResultStream()
}