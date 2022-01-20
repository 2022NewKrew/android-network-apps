package com.survivalcoding.network_apps.feature_pagination.domain.usecase

import com.survivalcoding.network_apps.feature_pagination.domain.model.User
import com.survivalcoding.network_apps.feature_pagination.domain.repository.PostItemRepository

class GetUsersUseCase(
    private val repository: PostItemRepository
) {
    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}