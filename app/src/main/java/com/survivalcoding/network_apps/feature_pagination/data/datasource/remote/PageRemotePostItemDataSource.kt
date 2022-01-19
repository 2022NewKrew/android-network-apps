package com.survivalcoding.network_apps.feature_pagination.data.datasource.remote

import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.service.PageResourceService
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem

class PageRemotePostItemDataSource(
    private val service: PageResourceService
) {
    suspend fun getPostItems(): List<PostItem> {
        val posts = service.getPosts()

        return posts.map {
            val user = service.getUserById(it.id)
            PostItem(it, user.name)
        }
    }
}