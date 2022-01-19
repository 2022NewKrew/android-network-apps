package com.survivalcoding.network_apps.di

import com.survivalcoding.network_apps.conference.data.datasource.ConferenceDataSource
import com.survivalcoding.network_apps.conference.data.repository.ConferenceRepositoryImpl
import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.data.repository.TodoRepositoryImpl
import com.survivalcoding.network_apps.conference.domain.repository.ConferenceRepository
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
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
}