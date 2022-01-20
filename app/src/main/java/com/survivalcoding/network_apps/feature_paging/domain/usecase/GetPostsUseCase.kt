package com.survivalcoding.network_apps.feature_paging.domain.usecase

import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository

class GetPostsUseCase(private val repository: PostRepository) {
    operator fun invoke() = repository.getPosts()
}