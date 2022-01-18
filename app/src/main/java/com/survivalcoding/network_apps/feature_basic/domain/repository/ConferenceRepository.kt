package com.survivalcoding.network_apps.feature_basic.domain.repository

import com.survivalcoding.network_apps.feature_basic.domain.model.ConferenceItem

interface ConferenceRepository {
    suspend fun getData(): List<ConferenceItem>?
}