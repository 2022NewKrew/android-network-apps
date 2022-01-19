package com.survivalcoding.network_apps.feature_conference.data.datasource

import com.survivalcoding.network_apps.feature_conference.data.datasource.ConferenceURL.Companion.CONFERENCE_URL
import com.survivalcoding.network_apps.feature_conference.domain.model.ConferenceItem
import retrofit2.Response
import retrofit2.http.GET

interface ConferenceService {
    @GET(CONFERENCE_URL)
    suspend fun getData(): Response<List<ConferenceItem>>
}