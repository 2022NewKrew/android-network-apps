package com.survivalcoding.network_apps.feature_conferences.data.datasource.remote.service

import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import retrofit2.http.GET

interface ConferenceService {
    @GET("/mock_json/main/conferences.json")
    suspend fun getConferenceList(): List<Conference>
}