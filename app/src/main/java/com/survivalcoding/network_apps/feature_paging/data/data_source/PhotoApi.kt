package com.survivalcoding.network_apps.feature_paging.data.data_source

import com.survivalcoding.network_apps.feature_paging.domain.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET("$baseUrl/photos")
    suspend fun getPhotos(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int = 10
    ): List<Photo>

    companion object {
        const val baseUrl = "https://jsonplaceholder.typicode.com"
    }
}