package com.survivalcoding.network_apps.feature_conference.domain.repository

import com.survivalcoding.network_apps.feature_conference.domain.model.ConferenceInfo

// 변경사항
interface ConferenceRepository {
    suspend fun getConferenceList(): List<ConferenceInfo>
}