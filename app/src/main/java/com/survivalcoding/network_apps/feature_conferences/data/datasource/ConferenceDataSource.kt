package com.survivalcoding.network_apps.feature_conferences.data.datasource

import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import kotlinx.coroutines.flow.Flow

interface ConferenceDataSource {
    suspend fun getConferenceList(): Flow<List<Conference>>
}