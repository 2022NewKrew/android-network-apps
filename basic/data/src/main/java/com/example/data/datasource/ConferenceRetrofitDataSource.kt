package com.example.data.datasource

import com.example.data.retrofit.ConferenceService
import com.example.domain.model.Conference
import java.net.ConnectException

class ConferenceRetrofitDataSource(private val conferenceRetrofitService: ConferenceService) :
    ConferenceRemoteDataSource {
    override suspend fun getConferences(): List<Conference> {
        conferenceRetrofitService.getConferences().let {
            if (it.isSuccessful) {
                return it.body() ?: listOf()
            } else {
                throw ConnectException()
            }
        }
    }
}