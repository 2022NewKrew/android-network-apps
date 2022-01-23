package com.survivalcoding.network_apps.feature_paging.di

import com.survivalcoding.network_apps.feature_paging.data.datasource.remote.RetrofitClient
import com.survivalcoding.network_apps.feature_paging.data.datasource.remote.service.PostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providePostService(): PostService {
        return RetrofitClient.getClient().create(PostService::class.java)
    }
}