package com.survivalcoding.network_apps.feature_paging.data.repository

import com.survivalcoding.network_apps.feature_paging.data.datasource.PostDataSource
import com.survivalcoding.network_apps.feature_paging.domain.model.PostItem
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val postDataSource: PostDataSource) :
    PostRepository {
    override suspend fun getPosts(page: Int): List<PostItem> {
        val items = postDataSource.getPosts(page)
        val retVal = mutableListOf<PostItem>()

        items?.forEach { postCacheItem ->
            val user = postDataSource.getUserById(postCacheItem.userId)
            user?.let { userCacheItem -> retVal.add(PostItem(postCacheItem, userCacheItem)) }
        }

        return retVal
    }
}