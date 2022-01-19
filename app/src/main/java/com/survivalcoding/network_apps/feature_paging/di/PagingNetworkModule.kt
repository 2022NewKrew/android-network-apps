package com.survivalcoding.network_apps.feature_paging.di

import com.survivalcoding.network_apps.BuildConfig
import com.survivalcoding.network_apps.feature_paging.data.api.JsonPlaceHolderService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PagingNetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        // log
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }

        // timeout
        builder.readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
        builder.writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
        builder.connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideJsonPlaceholderService(okHttpClient: OkHttpClient): JsonPlaceHolderService {
        return Retrofit.Builder().baseUrl(JSON_PLACEHOLDER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(JsonPlaceHolderService::class.java)
    }

    companion object {
        private const val JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com/"
        private const val TIME_OUT_SECONDS = 2L
    }
}