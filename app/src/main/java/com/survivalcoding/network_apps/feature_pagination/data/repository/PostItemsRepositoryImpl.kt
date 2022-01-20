package com.survivalcoding.network_apps.feature_pagination.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.PageRemotePostItemDataSource
import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.model.User
import com.survivalcoding.network_apps.feature_pagination.domain.repository.PostItemRepository
import kotlinx.coroutines.flow.Flow

const val NETWORK_PAGE_SIZE = 20

class PostItemsRepositoryImpl(
    private val pagingSource: PageRemotePostItemDataSource,
) : PostItemRepository {
    override fun getPosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                pagingSource
            }
        ).flow
    }

    override suspend fun getUsers(): List<User> {
        return pagingSource.getUsers()
    }
}