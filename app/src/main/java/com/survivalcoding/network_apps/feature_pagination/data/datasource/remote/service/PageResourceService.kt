package com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.service

import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PageResourceService {
    @GET("/posts")
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") pageSize: Int,
    ): List<Post>

    @GET("/posts")
    suspend fun getPostsNotPage(): List<Post>

    @GET("/users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User
}