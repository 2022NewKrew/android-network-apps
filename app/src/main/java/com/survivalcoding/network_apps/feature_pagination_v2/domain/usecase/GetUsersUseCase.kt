package com.survivalcoding.network_apps.feature_pagination_v2.domain.usecase

import com.survivalcoding.network_apps.feature_pagination.domain.model.User
import com.survivalcoding.network_apps.feature_pagination_v2.domain.repository.PostRepository

class GetUsersUseCase(
    private val repository: PostRepository,
) : BaseUseCase() {
    suspend operator fun invoke(): Result<List<User>> {
        return result { repository.getUsers() }
    }
}