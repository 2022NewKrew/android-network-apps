package com.survivalcoding.network_apps.feature_paging.data.datasource.network.service

import com.survivalcoding.network_apps.feature_paging.data.dto.UserEntity
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonService {

    @GET("/posts")
    suspend fun getPosts(@Query("_page") page: Int, @Query("_limit") pageSize: Int): List<Post>

    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): UserEntity?
}