package com.survivalcoding.network_apps.di

import com.survivalcoding.network_apps.feature_conference.data.datasource.ConferenceService
import com.survivalcoding.network_apps.feature_conference.data.datasource.ConferenceURL
import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.TodoService
import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.TodoURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().readTimeout(9, TimeUnit.SECONDS)
            .connectTimeout(9, TimeUnit.SECONDS).addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TodoRetrofit

    @Singleton
    @Provides
    @TodoRetrofit
    fun provideTodoRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(TodoURL.BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ConferenceRetrofit

    @Singleton
    @Provides
    @ConferenceRetrofit
    fun provideConferenceRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(ConferenceURL.BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideTodoService(@TodoRetrofit retrofit: Retrofit): TodoService =
        retrofit.create(TodoService::class.java)

    @Singleton
    @Provides
    fun provideConferenceService(@ConferenceRetrofit retrofit: Retrofit): ConferenceService =
        retrofit.create(ConferenceService::class.java)

}