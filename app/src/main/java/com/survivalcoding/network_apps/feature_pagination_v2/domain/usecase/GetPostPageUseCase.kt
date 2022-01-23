package com.survivalcoding.network_apps.feature_pagination_v2.domain.usecase

import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination_v2.domain.repository.PostRepository

class GetPostPageUseCase(
    private val repository: PostRepository,
) {
    suspend operator fun invoke(page: Int, size: Int): List<Post> {
        return repository.getPostsPage(page, size)
    }
}