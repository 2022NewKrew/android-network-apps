package com.survivalcoding.network_apps.feature_conferences.data.datasource.remote

import com.survivalcoding.network_apps.feature_conferences.data.datasource.remote.service.ConferencesService
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference

class ConferencesNetworkSource(
    private val conferencesService: ConferencesService,
) {
    suspend fun getConferences(): ArrayList<Conference> {
        return conferencesService.getConferences()
    }
}