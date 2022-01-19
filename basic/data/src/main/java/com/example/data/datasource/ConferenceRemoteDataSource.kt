package com.example.data.datasource

import com.example.domain.model.Conference

interface ConferenceRemoteDataSource {
    suspend fun getConferences(): List<Conference>
}