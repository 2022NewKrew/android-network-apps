package com.survivalcoding.network_apps.feature_paging.domain.usecase

import com.survivalcoding.network_apps.feature_paging.domain.repository.JsonPlaceholderRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val jsonPlaceholderRepository: JsonPlaceholderRepository) {
    suspend operator fun invoke(page: Int, pageSize: Int) = jsonPlaceholderRepository.getPosts(page, pageSize)
}