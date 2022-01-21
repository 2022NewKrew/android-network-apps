package com.survivalcoding.network_apps.paging.data.datasource

import com.survivalcoding.network_apps.paging.domain.model.Post
import com.survivalcoding.network_apps.paging.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {

    @GET("/posts")
    suspend fun getPostsByPage(@Query("_page") page: Int, @Query("_limit") limit: Int): List<Post>

    @GET("/users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User
}