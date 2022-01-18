package com.survivalcoding.network_apps.feature_basic.data

import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.data.repository.TodoRepositoryImpl
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TodoRepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideTodoRepository(dataSource: TodoDataSource): TodoRepository = TodoRepositoryImpl(dataSource)
}