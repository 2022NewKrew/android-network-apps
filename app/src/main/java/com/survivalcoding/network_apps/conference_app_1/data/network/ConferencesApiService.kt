package com.survivalcoding.network_apps.conference_app_1.data.network

import com.survivalcoding.network_apps.conference_app_1.domain.model.Conference
import retrofit2.http.GET

interface ConferencesApiService {
    @GET("junsuk5/mock_json/main/conferences.json")
    suspend fun getConferences(): List<Conference>
}