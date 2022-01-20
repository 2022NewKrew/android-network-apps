package com.survivalcoding.network_apps.feature_conference.data.datasource.network

import com.survivalcoding.network_apps.feature_conference.data.datasource.DataSource
import com.survivalcoding.network_apps.feature_conference.data.datasource.network.service.ConferenceService
import com.survivalcoding.network_apps.feature_conference.domain.model.ConferenceInfo

// 변경사항
class RemoteCnfDataSource(private val service: ConferenceService) : DataSource {
    override suspend fun getConferenceList(): List<ConferenceInfo> = service.getList()
}