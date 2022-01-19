package com.example.paging.domain.usecase

import com.example.paging.domain.repository.JsonPlaceholderRepository

class GetUserUseCase constructor(private val jsonPlaceholderRepository: JsonPlaceholderRepository) {
    suspend operator fun invoke(id: Int) = jsonPlaceholderRepository.getUser(id)
}