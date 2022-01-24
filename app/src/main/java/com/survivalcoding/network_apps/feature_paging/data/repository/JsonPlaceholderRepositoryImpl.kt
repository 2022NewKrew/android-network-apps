package com.survivalcoding.network_apps.feature_paging.data.repository

import com.survivalcoding.network_apps.feature_paging.data.datasource.JsonPlaceHolderDataSource
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import com.survivalcoding.network_apps.feature_paging.domain.repository.JsonPlaceholderRepository
import retrofit2.HttpException
import javax.inject.Inject

class JsonPlaceholderRepositoryImpl @Inject constructor(private val jsonPlaceHolderDataSource: JsonPlaceHolderDataSource) :
    JsonPlaceholderRepository {
    override suspend fun getPosts(page: Int, pageSize: Int): List<Post> {
        val response = jsonPlaceHolderDataSource.getPosts(page, pageSize)

        if (response.isSuccessful) {
            return response.body() ?: listOf()
        }

        throw HttpException(response)
    }

    override suspend fun getUser(id: Int): User {
        val response = jsonPlaceHolderDataSource.getUser(id)
        val user = response.body()

        if (response.isSuccessful && user != null) {
            return user
        }

        throw HttpException(response)
    }
}