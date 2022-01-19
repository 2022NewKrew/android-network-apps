package com.example.paging.domain.usecase

import com.example.paging.domain.repository.JsonPlaceholderRepository

class GetPostsUseCase constructor(private val jsonPlaceholderRepository: JsonPlaceholderRepository) {
    suspend operator fun invoke(page: Int) = jsonPlaceholderRepository.getPosts(page)
}