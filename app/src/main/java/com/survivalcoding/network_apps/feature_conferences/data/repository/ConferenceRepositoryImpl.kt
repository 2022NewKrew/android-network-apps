package com.survivalcoding.network_apps.feature_conferences.data.repository

import com.survivalcoding.network_apps.feature_conferences.data.datasource.ConferenceDataSource
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import com.survivalcoding.network_apps.feature_conferences.domain.repository.ConferenceRepository

class ConferenceRepositoryImpl(private val dataSource: ConferenceDataSource) :
    ConferenceRepository {
    override suspend fun getConferenceList(): List<Conference> = dataSource.getConferenceList()
}