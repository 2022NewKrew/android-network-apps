package com.survivalcoding.network_apps.feature_conference.data.datasource

import com.survivalcoding.network_apps.feature_conference.domain.model.ConferenceInfo

// 변경사항
interface DataSource {
    suspend fun getConferenceList(): List<ConferenceInfo>
}