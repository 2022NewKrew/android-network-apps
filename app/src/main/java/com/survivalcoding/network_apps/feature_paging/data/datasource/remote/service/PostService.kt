package com.survivalcoding.network_apps.feature_paging.data.datasource.remote.service

import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import retrofit2.http.GET

interface PostService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>
}