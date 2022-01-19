package com.survivalcoding.network_apps.di

import com.survivalcoding.network_apps.feature_conference.data.datasource.ConferenceDataSource
import com.survivalcoding.network_apps.feature_conference.data.repository.ConferenceRepositoryImpl
import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.data.repository.TodoRepositoryImpl
import com.survivalcoding.network_apps.feature_conference.domain.repository.ConferenceRepository
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import com.survivalcoding.network_apps.feature_paging.data.datasource.PostDataSource
import com.survivalcoding.network_apps.feature_paging.data.repository.PostRepositoryImpl
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideTodoRepository(@DataSourceModule.TodoRemoteDataSourceQualifier dataSource: TodoDataSource): TodoRepository = TodoRepositoryImpl(dataSource)

    @Provides
    @ViewModelScoped
    fun provideConferenceRepository(dataSource: ConferenceDataSource): ConferenceRepository = ConferenceRepositoryImpl(dataSource)

    @Provides
    @ViewModelScoped
    fun providePostRepository(dataSource: PostDataSource): PostRepository = PostRepositoryImpl(dataSource)
}