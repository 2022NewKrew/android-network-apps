package com.survivalcoding.network_apps.feature_conferences.domain.repository

import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import kotlinx.coroutines.flow.Flow

interface ConferenceRepository {
    suspend fun getConferenceList(): Flow<List<Conference>>
}