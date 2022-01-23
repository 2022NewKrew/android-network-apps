package com.survivalcoding.network_apps.feature_pagination_v2.domain.usecase

import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination_v2.domain.repository.PostRepository

class GetPostsUseCase(
    private val repository: PostRepository,
) : BaseUseCase() {
    suspend operator fun invoke(): Result<List<Post>> {
        return result { repository.getPosts() }
    }
}