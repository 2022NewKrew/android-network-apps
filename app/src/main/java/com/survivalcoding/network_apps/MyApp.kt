package com.survivalcoding.network_apps

import android.app.Application
import com.survivalcoding.network_apps.conference.data.datasource.network.RemoteCnfDataSource
import com.survivalcoding.network_apps.conference.data.datasource.network.service.ConferenceService
import com.survivalcoding.network_apps.conference.data.repository.ConferenceRepositoryImpl
import com.survivalcoding.network_apps.feature_basic.data.datasource.network.RemoteDataSource
import com.survivalcoding.network_apps.feature_basic.data.datasource.network.service.SampleJsonService
import com.survivalcoding.network_apps.feature_basic.data.repository.RemoteTodoRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application() {


    private val sampleRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
    }

    private val conferenceRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://raw.githubusercontent.com/")
            .build()
    }


    val conferenceRepository: ConferenceRepositoryImpl by lazy {
        ConferenceRepositoryImpl(
            RemoteCnfDataSource(
                conferenceRetrofit.create(ConferenceService::class.java)
            )
        )
    }

    val sampleRepository: RemoteTodoRepositoryImpl by lazy {
        RemoteTodoRepositoryImpl(
            RemoteDataSource(
                sampleRetrofit.create(SampleJsonService::class.java)
            )
        )
    }

}