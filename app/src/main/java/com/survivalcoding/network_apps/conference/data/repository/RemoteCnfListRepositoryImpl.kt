package com.survivalcoding.network_apps.conference.data.repository

import com.survivalcoding.network_apps.conference.data.datasource.network.RemoteCnfDataSource
import com.survivalcoding.network_apps.conference.domain.model.ConferenceInfo
import com.survivalcoding.network_apps.conference.domain.repository.ConferenceRepository

// 변경사항
class RemoteCnfListRepositoryImpl(private val remoteCnfDataSource: RemoteCnfDataSource) : ConferenceRepository {
    override suspend fun getConferenceList(): List<ConferenceInfo> = remoteCnfDataSource.getConferenceList()
}