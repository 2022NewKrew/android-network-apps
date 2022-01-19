package com.survivalcoding.network_apps.feature_pagination.data.repository

import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.PageRemoteNotPagingDataSource
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem

class PostItemsNotPageRepository(
    private val dataSource: PageRemoteNotPagingDataSource
) {
    suspend fun getPostItems(): List<PostItem> {
        return dataSource.getPostItems()
    }
}