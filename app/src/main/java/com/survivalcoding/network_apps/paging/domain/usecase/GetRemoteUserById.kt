package com.survivalcoding.network_apps.paging.domain.usecase

import com.survivalcoding.network_apps.paging.domain.model.User
import com.survivalcoding.network_apps.paging.domain.repository.PostRepository

class GetRemoteUserById(private val repository: PostRepository) {
    suspend operator fun invoke(id: Int): User? {
        return try {
            repository.getUserById(id)
        } catch (e: Exception) {
            null
        }
    }
}