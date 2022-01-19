package com.survivalcoding.network_apps.conference.data.repository

import com.survivalcoding.network_apps.conference.data.datasource.ConferenceDataSource
import com.survivalcoding.network_apps.conference.domain.repository.ConferenceRepository
import javax.inject.Inject

class ConferenceRepositoryImpl @Inject constructor(private val conferenceDataSource: ConferenceDataSource) :
    ConferenceRepository {
    override suspend fun getData() = conferenceDataSource.getData()
}