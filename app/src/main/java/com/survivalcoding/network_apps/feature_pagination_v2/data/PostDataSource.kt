package com.survivalcoding.network_apps.feature_pagination_v2.data

import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.model.User

class PostDataSource(
    private val service: PostService
) {
    suspend fun getUsers(): List<User> {
        return service.getUsers()
    }

    suspend fun getUserById(id: Int): User {
        return service.getUserById(id)
    }

    suspend fun getPosts(): List<Post> {
        return service.getPostsNotPage()
    }

    suspend fun getPostsPage(page: Int, size: Int): List<Post> {
        return service.getPosts(page, size)
    }
}