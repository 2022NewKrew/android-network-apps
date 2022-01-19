package com.survivalcoding.network_apps.paging.data.datasource.remote

import com.survivalcoding.network_apps.paging.domain.model.Post
import retrofit2.http.GET
import retrofit2.http.Query


interface PostApi {
    @GET("posts")
    suspend fun getPosts(@Query("page") page: Int, @Query("limit") limit: Int): List<Post>
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}