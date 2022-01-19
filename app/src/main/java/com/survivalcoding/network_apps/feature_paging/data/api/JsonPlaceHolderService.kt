package com.survivalcoding.network_apps.feature_paging.data.api

import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceHolderService {
    @GET("/posts")
    suspend fun getPosts(@Query("_page") page: Int): Response<List<Post>>

    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<User>
}