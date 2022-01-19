package com.survivalcoding.network_apps.feature_paging.data.repositoryimpl

import com.survivalcoding.network_apps.feature_paging.data.datasource.network.PostDataSource
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository

// 변경사항
class PostRepositoryImpl(private val postDataSource: PostDataSource) :
    PostRepository {
    override suspend fun getPosts(page: Int): List<Post> = postDataSource.getPosts(page)

}