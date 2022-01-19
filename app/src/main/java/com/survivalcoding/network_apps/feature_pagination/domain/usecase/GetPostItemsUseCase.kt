package com.survivalcoding.network_apps.feature_pagination.domain.usecase

import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem
import com.survivalcoding.network_apps.feature_pagination.domain.repository.PostItemRepository
import kotlinx.coroutines.flow.Flow

class GetPostItemsUseCase(
    private val repository: PostItemRepository
) : BaseUseCase() {
    suspend operator fun invoke(): Result<Flow<PagingData<PostItem>>> {
        return result { repository.getPostItems() }
    }
}