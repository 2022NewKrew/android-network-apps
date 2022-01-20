package com.survivalcoding.network_apps.feature_paging.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_paging.data.datasource.remote.RemotePostDataSource
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(private val dataSource: RemotePostDataSource) : PostRepository {
    override fun getPosts(): Flow<PagingData<Post>> {
        return Pager(PagingConfig(pageSize = 20)) {
            dataSource
        }.flow
    }
}