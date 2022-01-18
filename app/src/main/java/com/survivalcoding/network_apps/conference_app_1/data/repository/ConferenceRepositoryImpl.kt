package com.survivalcoding.network_apps.conference_app_1.data.repository

import com.survivalcoding.network_apps.conference_app_1.data.datasource.ConferenceRemoteDataSource
import com.survivalcoding.network_apps.conference_app_1.domain.model.Conference
import com.survivalcoding.network_apps.conference_app_1.domain.repository.ConferenceRepository

class ConferenceRepositoryImpl(
    private val conferenceRemoteDataSource: ConferenceRemoteDataSource
) :
    ConferenceRepository {
    override suspend fun getConferences(): List<Conference> =
        conferenceRemoteDataSource.getConferences()
}

