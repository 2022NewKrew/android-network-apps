package com.survivalcoding.network_apps.feature_pagination.domain.repository

import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem

interface PostItemRepository {
    suspend fun getPostItems(): List<PostItem>
}