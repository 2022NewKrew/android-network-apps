package com.example.data

import com.example.data.datasource.ConferenceRemoteDataSource
import com.example.data.datasource.ConferenceRetrofitDataSource
import com.example.data.repository.ConferenceRepositoryImpl
import com.example.data.retrofit.ConferenceService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Data {

    private const val CONFERENCE_SERVER_BASE_URL = "https://raw.githubusercontent.com/"

    private val conferenceService: ConferenceService by lazy {
        Retrofit.Builder()
            .baseUrl(CONFERENCE_SERVER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConferenceService::class.java)
    }

    private val conferenceRemoteDataSource: ConferenceRemoteDataSource by lazy {
        ConferenceRetrofitDataSource(conferenceService)
    }

    val conferenceRepository by lazy {
        ConferenceRepositoryImpl(conferenceRemoteDataSource)
    }
}