package com.survivalcoding.network_apps.feature_paging.data.datasource

import com.survivalcoding.network_apps.feature_paging.data.datasource.PostURL.POSTS_URL
import com.survivalcoding.network_apps.feature_paging.data.datasource.PostURL.USERS_URL
import com.survivalcoding.network_apps.feature_paging.domain.model.PostCacheItem
import com.survivalcoding.network_apps.feature_paging.domain.model.UserCacheItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @GET(POSTS_URL)
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): Response<List<PostCacheItem>>

    @GET("${USERS_URL}/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<UserCacheItem>
}