package com.survivalcoding.network_apps.conference_app_1.data.datasource

import com.survivalcoding.network_apps.conference_app_1.data.network.ConferencesApi
import com.survivalcoding.network_apps.conference_app_1.domain.model.Conference

class ConferenceRemoteDataSource(private val conferenceApi: ConferencesApi) {
    suspend fun getConferences(): List<Conference> = conferenceApi.retrofitService.getConferences()
}