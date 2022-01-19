package com.survivalcoding.network_apps.feature_paging.data.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_paging.data.datasource.network.PostPagingDataSource
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

// 변경사항
class PostRepositoryImpl(private val postPagingDataSource: PostPagingDataSource) :
    PostRepository {
    override fun getPosts(): Flow<PagingData<Post>> =
        Pager(PagingConfig(pageSize = 20),
            pagingSourceFactory = { postPagingDataSource }).flow


}