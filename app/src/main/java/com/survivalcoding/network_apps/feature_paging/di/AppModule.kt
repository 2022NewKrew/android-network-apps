package com.survivalcoding.network_apps.feature_paging.di

import com.survivalcoding.network_apps.feature_paging.data.RetrofitClient
import com.survivalcoding.network_apps.feature_paging.data.repository.PhotoRepositoryImpl
import com.survivalcoding.network_apps.feature_paging.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePhotoRepository(): PhotoRepository {
        return PhotoRepositoryImpl(RetrofitClient.apiService)
    }
}