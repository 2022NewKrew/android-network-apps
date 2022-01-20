package com.survivalcoding.network_apps.paging.domain.repository

import com.survivalcoding.network_apps.paging.domain.model.Post
import com.survivalcoding.network_apps.paging.domain.model.User

interface PostRepository {

    suspend fun getPostsByPage(page: Int, limit: Int): List<Post>

    suspend fun getUserById(id: Int): User
}