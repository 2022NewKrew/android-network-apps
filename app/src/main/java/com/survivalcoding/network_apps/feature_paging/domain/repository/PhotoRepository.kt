package com.survivalcoding.network_apps.feature_paging.domain.repository

import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_paging.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotos(): Flow<PagingData<Photo>>
}