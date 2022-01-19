package com.survivalcoding.network_apps.conference.data.datasource.network.service

import com.survivalcoding.network_apps.conference.domain.model.ConferenceInfo
import retrofit2.http.GET

interface ConferenceService {
    // 변경사항
    @GET("/junsuk5/mock_json/main/conferences.json")
    suspend fun getList(): List<ConferenceInfo>
}