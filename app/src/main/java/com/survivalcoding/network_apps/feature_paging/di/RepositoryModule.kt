package com.survivalcoding.network_apps.feature_paging.di

import com.survivalcoding.network_apps.feature_paging.data.repository.JsonPlaceholderRepositoryImpl
import com.survivalcoding.network_apps.feature_paging.domain.repository.JsonPlaceholderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindJsonPlaceholderRepository(jsonPlaceholderRepositoryImpl: JsonPlaceholderRepositoryImpl): JsonPlaceholderRepository
}