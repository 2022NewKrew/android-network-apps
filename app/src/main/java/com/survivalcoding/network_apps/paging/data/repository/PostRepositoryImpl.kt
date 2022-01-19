package com.survivalcoding.network_apps.paging.data.repository

import com.survivalcoding.network_apps.paging.data.datasource.remote.PostRemoteDataSource

class PostRepositoryImpl(
    private val postRemoteDataSource: PostRemoteDataSource
) {
    fun getResultStream() = postRemoteDataSource.getResultStream()
}