package com.survivalcoding.network_apps.paging.domain.repository

import androidx.paging.PagingData
import com.survivalcoding.network_apps.paging.domain.model.PostWithName
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getResultStream(): Flow<PagingData<PostWithName>>
}