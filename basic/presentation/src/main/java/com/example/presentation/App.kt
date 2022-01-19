package com.example.presentation

import android.app.Application
import com.example.data.datasource.ConferenceRemoteDataSource
import com.example.data.datasource.ConferenceRetrofitDataSource
import com.example.data.repository.ConferenceRepositoryImpl
import com.example.data.retrofit.ConferenceService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class App : Application() {

    private val conferenceService: ConferenceService by lazy {
        Retrofit.Builder()
            .baseUrl(CONFERENCE_SERVER_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ConferenceService::class.java)
    }

    private val conferenceRemoteDataSource: ConferenceRemoteDataSource by lazy {
        ConferenceRetrofitDataSource(conferenceService)
    }

    val conferenceRepository by lazy {
        ConferenceRepositoryImpl(conferenceRemoteDataSource)
    }

    companion object {
        private const val CONFERENCE_SERVER_BASE_URL = "https://raw.githubusercontent.com/"
    }
}