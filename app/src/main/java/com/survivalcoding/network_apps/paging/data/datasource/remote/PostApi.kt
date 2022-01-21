package com.survivalcoding.network_apps.paging.data.datasource.remote

import com.survivalcoding.network_apps.paging.domain.model.Post
import com.survivalcoding.network_apps.paging.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PostApi {
    @GET("posts")
    suspend fun getPosts(@Query("_page") page: Int, @Query("_limit") limit: Int): List<Post>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User?

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}