package com.survivalcoding.network_apps.conference.data.datasource.network

import com.survivalcoding.network_apps.conference.data.datasource.DataSource
import com.survivalcoding.network_apps.conference.data.datasource.network.service.ConferenceService
import com.survivalcoding.network_apps.conference.domain.model.ConferenceInfo


class RemoteCnfDataSource(private val service: ConferenceService) : DataSource {
    override suspend fun getConferenceList(): List<ConferenceInfo> = service.getList()
}