package com.survivalcoding.network_apps.feature_basic.data

import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.data.datasource.local.LocalTodoDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoDataSourceModule {
    @Provides
    @Singleton
    fun provideDataSource(): TodoDataSource = LocalTodoDataSource()
}