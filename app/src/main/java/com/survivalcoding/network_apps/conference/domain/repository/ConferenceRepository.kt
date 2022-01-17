package com.survivalcoding.network_apps.conference.domain.repository
import com.survivalcoding.network_apps.conference.domain.model.ConferenceInfo


interface ConferenceRepository {
    suspend fun getConferenceList(): List<ConferenceInfo>
}