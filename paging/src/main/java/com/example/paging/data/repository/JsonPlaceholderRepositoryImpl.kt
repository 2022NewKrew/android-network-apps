package com.example.paging.data.repository

import com.example.paging.data.datasource.JsonPlaceHolderDataSource
import com.example.paging.domain.model.Post
import com.example.paging.domain.model.User
import com.example.paging.domain.repository.JsonPlaceholderRepository
import retrofit2.HttpException
import javax.inject.Inject

class JsonPlaceholderRepositoryImpl @Inject constructor(private val jsonPlaceHolderDataSource: JsonPlaceHolderDataSource) :
    JsonPlaceholderRepository {
    override suspend fun getPosts(page: Int): List<Post> {
        val response = jsonPlaceHolderDataSource.getPosts(page)

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