package com.survivalcoding.network_apps.feature_pagination.domain.usecase

import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.repository.PostItemRepository
import kotlinx.coroutines.flow.Flow

class GetPostsUseCase(
    private val repository: PostItemRepository
) : BaseUseCase() {
    operator fun invoke(): Flow<PagingData<Post>> {
        return repository.getPosts()
    }
}