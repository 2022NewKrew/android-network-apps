package com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.service

import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface PageResourceService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @GET("/users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User
}