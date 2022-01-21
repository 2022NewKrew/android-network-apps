package com.survivalcoding.network_apps.feature_paging.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_paging.data.datasource.PostDataSource
import com.survivalcoding.network_apps.feature_paging.domain.model.PostItem
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostPagingConstants.REQUEST_PAGE_SIZE
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val postDataSource: PostDataSource) :
    PostRepository {
    override fun getPosts(): Flow<PagingData<PostItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = REQUEST_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { postDataSource }
        ).flow
    }
}