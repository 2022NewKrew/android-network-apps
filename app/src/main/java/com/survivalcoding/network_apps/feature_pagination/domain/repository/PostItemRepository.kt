package com.survivalcoding.network_apps.feature_pagination.domain.repository

import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.model.User
import kotlinx.coroutines.flow.Flow

interface PostItemRepository {
    fun getPosts(): Flow<PagingData<Post>>
    suspend fun getUsers(): List<User>
}