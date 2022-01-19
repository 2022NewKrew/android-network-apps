package com.example.paging.data.datasource

import com.example.paging.data.api.JsonPlaceHolderService
import javax.inject.Inject

class JsonPlaceHolderDataSource @Inject constructor(private val jsonPlaceHolderService: JsonPlaceHolderService) {
    suspend fun getPosts(page: Int) = jsonPlaceHolderService.getPosts(page)
    suspend fun getUser(id: Int) = jsonPlaceHolderService.getUser(id)
}