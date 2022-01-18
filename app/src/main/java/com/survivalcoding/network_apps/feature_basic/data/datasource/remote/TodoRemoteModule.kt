package com.survivalcoding.network_apps.feature_basic.data.datasource.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoRemoteModule {
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(9, TimeUnit.SECONDS)
            .connectTimeout(9, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    fun provideConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    fun provideCurrencyService(retrofit: Retrofit): TodoService =
        retrofit.create(TodoService::class.java)

}