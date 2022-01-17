package com.survivalcoding.network_apps

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.BasicTodoNetworkSource
import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.service.BasicTodoService
import com.survivalcoding.network_apps.feature_basic.data.repository.TodoRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class App : Application() {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val basicRetrofit = Retrofit.Builder()
        .baseUrl(BasicTodoService.BASIC_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val basicService = basicRetrofit.create(BasicTodoService::class.java)

    val basicRepository = TodoRepositoryImpl(BasicTodoNetworkSource(basicService))
}