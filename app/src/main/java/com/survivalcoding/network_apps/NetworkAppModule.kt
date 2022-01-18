package com.survivalcoding.network_apps

import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.TodoService
import com.survivalcoding.network_apps.feature_basic.data.repository.TodoRemoteRepository
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkAppModule {

    @Singleton
    @Provides
    fun provideTodoService(): TodoService {
        return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create()).build()
            .create(TodoService::class.java)
    }

    @Provides
    fun provideTodoRemoteRepository(todoService: TodoService): TodoRepository {
        return  TodoRemoteRepository(todoService)
    }
}