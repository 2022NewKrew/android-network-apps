package com.example.data.repository

import com.example.data.datasource.ConferenceRemoteDataSource
import com.example.domain.repository.ConferenceRepository

class ConferenceRepositoryImpl(private val conferenceRemoteDataSource: ConferenceRemoteDataSource) : ConferenceRepository {
    override suspend fun getConferences() = conferenceRemoteDataSource.getConferences()
}