package com.survivalcoding.network_apps.conferences.data.datasource.remote.service

import com.survivalcoding.network_apps.conferences.domain.model.Conference
import retrofit2.http.GET

interface ConferencesService {
    @GET("/conferences.json")
    suspend fun getConferences(): ArrayList<Conference>

    companion object {
        const val CONFERENCES_BASE_URL = "https://raw.githubusercontent.com/junsuk5/mock_json/main"
    }
}