package com.survivalcoding.network_apps.feature_conferences.data.datasource

import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference

interface ConferenceDataSource {
    suspend fun getConferenceList(): List<Conference>
}