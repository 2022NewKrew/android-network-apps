package com.survivalcoding.network_apps.paging.data.repository

import androidx.paging.PagingData
import com.survivalcoding.network_apps.paging.data.datasource.remote.PostRemoteDataSource
import com.survivalcoding.network_apps.paging.domain.model.Post
import com.survivalcoding.network_apps.paging.domain.model.User
import com.survivalcoding.network_apps.paging.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(
    private val postRemoteDataSource: PostRemoteDataSource
) : PostRepository {
    override fun getPostStream(): Flow<PagingData<Post>> = postRemoteDataSource.getPostStream()
    override suspend fun getUserById(id: Int): User? = try {
        postRemoteDataSource.getUserById(id)
    } catch (e: Exception) {
        null
    }
}