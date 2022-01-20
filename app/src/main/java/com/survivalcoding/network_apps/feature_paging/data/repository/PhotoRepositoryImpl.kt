package com.survivalcoding.network_apps.feature_paging.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_paging.data.data_source.PhotoApi
import com.survivalcoding.network_apps.feature_paging.data.data_source.PhotoRemotePagingSource
import com.survivalcoding.network_apps.feature_paging.domain.model.Photo
import com.survivalcoding.network_apps.feature_paging.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow

class PhotoRepositoryImpl(
    private val api: PhotoApi
) : PhotoRepository {

    override fun getPhotos(): Flow<PagingData<Photo>> = Pager(
        PagingConfig(pageSize = 10)
    ) {
        PhotoRemotePagingSource(api)
    }.flow
}