package com.survivalcoding.network_apps.feature_pagination.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.PageRemotePostItemDataSource
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem
import com.survivalcoding.network_apps.feature_pagination.domain.repository.PostItemRepository
import kotlinx.coroutines.flow.Flow

const val NETWORK_PAGE_SIZE = 20

class PostItemsRepositoryImpl(
    private val datasource: PageRemotePostItemDataSource
) : PostItemRepository {
    override suspend fun getPostItems(): Flow<PagingData<PostItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                datasource
            }
        ).flow
    }
}