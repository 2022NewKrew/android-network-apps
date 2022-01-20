package com.survivalcoding.network_apps.feature_pagination.data.datasource.remote

import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.service.PageResourceService
import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem
import com.survivalcoding.network_apps.feature_pagination.domain.model.User

class PageRemoteNotPagingDataSource(
    private val service: PageResourceService
) : PageRemoteDataSource {
    override suspend fun getUsers(): List<User> {
        return service.getUsers()
    }

    override suspend fun getPosts(): List<Post> {
        return service.getPostsNotPage()
    }
}