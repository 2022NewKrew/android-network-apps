package com.survivalcoding.network_apps.feature_paging.data.datasource.network

import com.survivalcoding.network_apps.feature_paging.data.datasource.network.service.JsonService
import com.survivalcoding.network_apps.feature_paging.domain.model.Post


class PostDataSource(private val service: JsonService) {
    suspend fun getPosts(page: Int): List<Post> = service.getPosts(page)
}