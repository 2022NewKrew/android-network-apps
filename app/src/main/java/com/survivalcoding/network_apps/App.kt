package com.survivalcoding.network_apps

import android.app.Application
import com.survivalcoding.network_apps.feature_conferences.data.datasource.remote.ConferencesNetworkSource
import com.survivalcoding.network_apps.feature_conferences.data.datasource.remote.service.ConferencesService
import com.survivalcoding.network_apps.feature_conferences.data.repository.ConferenceRepositoryImpl
import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.BasicTodoNetworkSource
import com.survivalcoding.network_apps.feature_basic.data.datasource.remote.service.BasicTodoService
import com.survivalcoding.network_apps.feature_basic.data.repository.TodoRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    private val basicRetrofit = Retrofit.Builder()
        .baseUrl(BasicTodoService.BASIC_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val basicService = basicRetrofit.create(BasicTodoService::class.java)

    val basicRepository = TodoRepositoryImpl(BasicTodoNetworkSource(basicService))

    private val conferenceRetrofit = Retrofit.Builder()
        .baseUrl(ConferencesService.CONFERENCES_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val conferencesService = conferenceRetrofit.create(ConferencesService::class.java)

    val conferencesRepository =
        ConferenceRepositoryImpl(ConferencesNetworkSource(conferencesService))
}