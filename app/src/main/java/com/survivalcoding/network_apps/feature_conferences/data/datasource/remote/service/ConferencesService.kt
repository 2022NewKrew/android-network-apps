package com.survivalcoding.network_apps.feature_conferences.data.datasource.remote.service

import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import retrofit2.http.GET

interface ConferencesService {
    @GET("/junsuk5/mock_json/main/conferences.json")
    suspend fun getConferences(): ArrayList<Conference>

    companion object {
        const val CONFERENCES_BASE_URL = "https://raw.githubusercontent.com"
    }
}