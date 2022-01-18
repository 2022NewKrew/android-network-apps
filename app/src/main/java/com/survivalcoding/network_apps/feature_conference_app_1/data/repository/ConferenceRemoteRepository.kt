package com.survivalcoding.network_apps.feature_conference_app_1.data.repository

import com.survivalcoding.network_apps.feature_conference_app_1.data.datasource.ConferenceService
import com.survivalcoding.network_apps.feature_conference_app_1.domain.model.Conference
import com.survivalcoding.network_apps.feature_conference_app_1.domain.repository.ConferenceRepository

class ConferenceRemoteRepository(private val conferenceService: ConferenceService): ConferenceRepository {
    override suspend fun getConferences(): List<Conference> {
        return conferenceService.getConferences()
    }
}