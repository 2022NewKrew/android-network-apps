package com.survivalcoding.network_apps

import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.TodoService
import com.survivalcoding.network_apps.feature_basic.data.repository.TodoRemoteRepository
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import com.survivalcoding.network_apps.feature_conference_app_1.data.datasource.ConferenceService
import com.survivalcoding.network_apps.feature_conference_app_1.data.repository.ConferenceRemoteRepository
import com.survivalcoding.network_apps.feature_conference_app_1.domain.repository.ConferenceRepository
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
        return TodoRemoteRepository(todoService)
    }

    @Singleton
    @Provides
    fun provideConferenceService(): ConferenceService {
        return Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(MoshiConverterFactory.create()).build()
            .create(ConferenceService::class.java)
    }

    @Provides
    fun provideConferenceRemoteRepository(conferenceService: ConferenceService): ConferenceRepository {
        return ConferenceRemoteRepository(conferenceService)
    }
}