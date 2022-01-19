package com.survivalcoding.network_apps.conference.data.repository

import com.survivalcoding.network_apps.conference.data.datasource.DataSource
import com.survivalcoding.network_apps.conference.domain.model.ConferenceInfo
import com.survivalcoding.network_apps.conference.domain.repository.ConferenceRepository

// 변경사항
class ConferenceRepositoryImpl(private val remoteCnfDataSource: DataSource) :
    ConferenceRepository {
    override suspend fun getConferenceList(): List<ConferenceInfo> =
        remoteCnfDataSource.getConferenceList()
}