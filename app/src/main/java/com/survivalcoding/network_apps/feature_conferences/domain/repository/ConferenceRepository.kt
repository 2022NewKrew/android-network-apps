package com.survivalcoding.network_apps.feature_conferences.domain.repository

import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference

interface ConferenceRepository {
    suspend fun getConferences(): List<Conference>
}