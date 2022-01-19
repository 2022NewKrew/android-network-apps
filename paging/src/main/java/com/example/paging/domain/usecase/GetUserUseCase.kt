package com.example.paging.domain.usecase

import com.example.paging.domain.repository.JsonPlaceholderRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val jsonPlaceholderRepository: JsonPlaceholderRepository) {
    suspend operator fun invoke(id: Int) = jsonPlaceholderRepository.getUser(id)
}