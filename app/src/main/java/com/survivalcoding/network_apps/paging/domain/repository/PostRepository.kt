package com.survivalcoding.network_apps.paging.domain.repository

import androidx.paging.PagingData
import com.survivalcoding.network_apps.paging.domain.model.Post
import com.survivalcoding.network_apps.paging.domain.model.User
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPostStream(): Flow<PagingData<Post>>
    suspend fun getUserById(id: Int): User?
}