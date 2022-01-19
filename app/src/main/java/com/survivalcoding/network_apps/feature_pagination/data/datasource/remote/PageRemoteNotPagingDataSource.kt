package com.survivalcoding.network_apps.feature_pagination.data.datasource.remote

import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.service.PageResourceService
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem

class PageRemoteNotPagingDataSource {
    private val service = PageRetrofitClient.getClient().create(PageResourceService::class.java)

    suspend fun getPostItems(): List<PostItem> {
        return service.getPostsNotPage().map {
            PostItem(it, service.getUserById(it.userId).name)
        }
    }
}