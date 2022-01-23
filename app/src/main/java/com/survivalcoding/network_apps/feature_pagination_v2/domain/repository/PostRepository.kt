package com.survivalcoding.network_apps.feature_pagination_v2.domain.repository

import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.model.User

interface PostRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getUsers(): List<User>
    suspend fun getUserById(id: Int): User
    suspend fun getPostsPage(page: Int, size: Int): List<Post>
}