package com.survivalcoding.network_apps.feature_pagination.data.repository

import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.PageRemotePostItemDataSource
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem
import com.survivalcoding.network_apps.feature_pagination.domain.repository.PostItemRepository

class PostItemsRepositoryImpl(
    private val datasource: PageRemotePostItemDataSource
) : PostItemRepository {
    override suspend fun getPostItems(): List<PostItem> {
        return datasource.getPostItems()
    }
}