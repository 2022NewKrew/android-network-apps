package com.survivalcoding.network_apps.conference.data.datasource

import com.survivalcoding.network_apps.conference.domain.model.ConferenceInfo

// 변경사항
interface DataSource {
    suspend fun getConferenceList(): List<ConferenceInfo>
}