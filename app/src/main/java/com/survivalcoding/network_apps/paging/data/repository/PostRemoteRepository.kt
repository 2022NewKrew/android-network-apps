package com.survivalcoding.network_apps.paging.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.survivalcoding.network_apps.paging.data.datasource.PostPagingSource
import com.survivalcoding.network_apps.paging.data.datasource.PostService
import com.survivalcoding.network_apps.paging.domain.model.Post
import com.survivalcoding.network_apps.paging.domain.model.User
import com.survivalcoding.network_apps.paging.domain.repository.PostRepository

class PostRemoteRepository(private val postService: PostService): PostRepository {

    override suspend fun getPostsByPage(page: Int, limit: Int): List<Post> {
        return postService.getPostsByPage(page, limit)
    }

    override suspend fun getUserById(id: Int): User {
        return postService.getUserById(id)
    }
}