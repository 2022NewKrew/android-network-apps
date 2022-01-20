package com.survivalcoding.network_apps.feature_paging.domain.usecase

import com.survivalcoding.network_apps.feature_paging.domain.repository.JsonPlaceholderRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val jsonPlaceholderRepository: JsonPlaceholderRepository) {
    suspend operator fun invoke(id: Int) = jsonPlaceholderRepository.getUser(id)
}