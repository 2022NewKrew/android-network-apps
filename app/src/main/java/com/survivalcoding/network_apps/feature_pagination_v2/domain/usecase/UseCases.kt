package com.survivalcoding.network_apps.feature_pagination_v2.domain.usecase

import com.survivalcoding.network_apps.feature_pagination_v2.domain.repository.PostRepository

class UseCases(
    repository: PostRepository
) {
    val getPostsUseCase = GetPostsUseCase(repository)
    val getPostPageUseCase = GetPostPageUseCase(repository)
    val getUsersUseCase = GetUsersUseCase(repository)
    val getUserByIdUseCase = GetUserByIdUseCase(repository)
}