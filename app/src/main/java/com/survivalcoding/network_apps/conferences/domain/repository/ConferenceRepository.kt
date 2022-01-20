package com.survivalcoding.network_apps.conferences.domain.repository

import com.survivalcoding.network_apps.conferences.domain.model.Conference

interface ConferenceRepository {
    suspend fun getConferences(): List<Conference>
}