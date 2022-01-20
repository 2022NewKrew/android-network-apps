package com.survivalcoding.network_apps.feature_conference.data.repository

import com.survivalcoding.network_apps.feature_conference.data.datasource.ConferenceDataSource
import com.survivalcoding.network_apps.feature_conference.domain.repository.ConferenceRepository
import javax.inject.Inject

class ConferenceRepositoryImpl @Inject constructor(private val conferenceDataSource: ConferenceDataSource) :
    ConferenceRepository {
    override suspend fun getData() = conferenceDataSource.getData()
}