package com.survivalcoding.network_apps.feature_paging.data.datasource

import com.survivalcoding.network_apps.feature_paging.domain.model.PostCacheItem
import com.survivalcoding.network_apps.feature_paging.domain.model.UserCacheItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostDataSource @Inject constructor(private val postsService: PostService) {
    suspend fun getPosts(page: Int): List<PostCacheItem>? {
        val response = postsService.getPosts(page = page)
        return if (response.isSuccessful) {
            response.body()
        } else {
            listOf()
        }
    }

    suspend fun getUserById(id: Int): UserCacheItem? {
        val response = postsService.getUserById(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}