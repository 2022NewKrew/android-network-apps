package com.survivalcoding.network_apps.feature_basic.data

import com.survivalcoding.network_apps.feature_basic.data.datasource.TodoDataSource
import com.survivalcoding.network_apps.feature_basic.data.datasource.local.LocalTodoDataSourceImpl
import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.RemoteTodoDataSourceImpl
import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.TodoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoDataSourceModule {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TodoRemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TodoLocalDataSource

    @Provides
    @TodoLocalDataSource
    @Singleton
    fun provideLocalDataSource(): TodoDataSource = LocalTodoDataSourceImpl()

    @Provides
    @TodoRemoteDataSource
    @Singleton
    fun provideRemoteDataSource(todoService: TodoService): TodoDataSource = RemoteTodoDataSourceImpl(todoService)
}