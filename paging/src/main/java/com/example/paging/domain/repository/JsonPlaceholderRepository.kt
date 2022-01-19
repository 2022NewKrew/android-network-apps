package com.example.paging.domain.repository

import com.example.paging.domain.model.Post
import com.example.paging.domain.model.User

interface JsonPlaceholderRepository {
    suspend fun getPosts(page: Int): List<Post>
    suspend fun getUser(id: Int): User
}