package com.survivalcoding.network_apps.feature_conferences.data.repository

import com.survivalcoding.network_apps.feature_conferences.data.datasource.remote.ConferencesNetworkSource
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import com.survivalcoding.network_apps.feature_conferences.domain.repository.ConferenceRepository

class ConferenceRepositoryImpl(
    private val dataSource: ConferencesNetworkSource
) : ConferenceRepository {
    override suspend fun getConferences(): List<Conference> {
        return dataSource.getConferences()
    }
}