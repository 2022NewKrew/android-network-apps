package com.survivalcoding.network_apps.feature_basic.data.conference.repository

import com.survivalcoding.network_apps.feature_basic.data.conference.datasource.ConferenceDataSource
import com.survivalcoding.network_apps.feature_basic.domain.repository.ConferenceRepository
import javax.inject.Inject

class ConferenceRepositoryImpl @Inject constructor(private val conferenceDataSource: ConferenceDataSource) :
    ConferenceRepository {
    override suspend fun getData() = conferenceDataSource.getData()
}