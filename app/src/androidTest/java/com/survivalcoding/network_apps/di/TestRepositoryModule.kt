package com.survivalcoding.network_apps.di

import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.data.repository.TodoRepositoryImpl
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import com.survivalcoding.network_apps.feature_conference.data.datasource.ConferenceDataSource
import com.survivalcoding.network_apps.feature_conference.data.repository.ConferenceRepositoryImpl
import com.survivalcoding.network_apps.feature_conference.domain.repository.ConferenceRepository
import com.survivalcoding.network_apps.feature_paging.data.datasource.PostDataSource
import com.survivalcoding.network_apps.feature_paging.data.repository.PostRepositoryImpl
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [RepositoryModule::class])
object TestRepositoryModule {

    @Provides
    @Singleton
    fun provideTodoRepository(@DataSourceModule.TodoRemoteDataSourceQualifier dataSource: TodoDataSource): TodoRepository = TodoRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideConferenceRepository(dataSource: ConferenceDataSource): ConferenceRepository = ConferenceRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun providePostRepository(dataSource: PostDataSource): PostRepository = PostRepositoryImpl(dataSource)
}