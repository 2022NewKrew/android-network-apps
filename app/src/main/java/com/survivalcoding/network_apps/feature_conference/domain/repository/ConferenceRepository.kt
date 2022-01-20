package com.survivalcoding.network_apps.feature_conference.domain.repository

import com.survivalcoding.network_apps.feature_conference.domain.model.ConferenceItem

interface ConferenceRepository {
    suspend fun getData(): List<ConferenceItem>?
}