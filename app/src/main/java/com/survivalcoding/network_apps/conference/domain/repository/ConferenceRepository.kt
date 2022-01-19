package com.survivalcoding.network_apps.conference.domain.repository

import com.survivalcoding.network_apps.conference.domain.model.ConferenceItem

interface ConferenceRepository {
    suspend fun getData(): List<ConferenceItem>?
}