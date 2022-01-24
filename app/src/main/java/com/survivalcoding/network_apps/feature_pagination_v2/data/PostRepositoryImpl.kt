package com.survivalcoding.network_apps.feature_pagination_v2.data

import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.model.User
import com.survivalcoding.network_apps.feature_pagination_v2.domain.repository.PostRepository

class PostRepositoryImpl(
    private val dataSource: PostDataSource
) : PostRepository {
    override suspend fun getPosts(): List<Post> {
        return dataSource.getPosts()
    }

    override suspend fun getUsers(): List<User> {
        return dataSource.getUsers()
    }

    override suspend fun getUserById(id: Int): User {
        return dataSource.getUserById(id)
    }

    override suspend fun getPostsPage(page: Int, size: Int): List<Post> {
        return dataSource.getPostsPage(page, size)
    }
}