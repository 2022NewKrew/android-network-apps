package com.survivalcoding.network_apps.feature_conference_app_1.data.datasource

import com.survivalcoding.network_apps.feature_conference_app_1.domain.model.Conference
import retrofit2.http.GET

interface ConferenceService {

    @GET("junsuk5/mock_json/main/conferences.json")
    suspend fun getConferences(): List<Conference>
}