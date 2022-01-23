package com.survivalcoding.network_apps.feature_pagination_v2.data

import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @GET("/posts")
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") pageSize: Int,
    ): List<Post>

    @GET("/posts")
    suspend fun getPostsNotPage(): List<Post>

    @GET("/users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User

    @GET("/users")
    suspend fun getUsers(): List<User>
}