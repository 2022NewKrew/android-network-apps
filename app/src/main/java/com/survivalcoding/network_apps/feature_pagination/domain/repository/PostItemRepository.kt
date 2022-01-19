package com.survivalcoding.network_apps.feature_pagination.domain.repository

import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem
import kotlinx.coroutines.flow.Flow

interface PostItemRepository {
    fun getPostItems(): Flow<PagingData<PostItem>>
}