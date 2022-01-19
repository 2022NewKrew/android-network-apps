package com.survivalcoding.network_apps.di

import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.data.datasource.local.LocalTodoDataSource
import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.RemoteTodoDataSource
import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.TodoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TodoRemoteDataSourceQualifier

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TodoLocalDataSourceQualifier

    @Provides
    @TodoLocalDataSourceQualifier
    @Singleton
    fun provideLocalDataSource(): TodoDataSource = LocalTodoDataSource()

    @Provides
    @TodoRemoteDataSourceQualifier
    @Singleton
    fun provideRemoteDataSource(todoService: TodoService): TodoDataSource = RemoteTodoDataSource(todoService)
}