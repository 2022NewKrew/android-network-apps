package com.survivalcoding.network_apps.conference.data.datasource

import com.survivalcoding.network_apps.conference.domain.model.ConferenceInfo


interface DataSource {
    suspend fun getConferenceList(): List<ConferenceInfo>
}