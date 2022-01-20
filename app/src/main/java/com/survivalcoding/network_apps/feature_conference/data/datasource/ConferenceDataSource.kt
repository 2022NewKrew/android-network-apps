package com.survivalcoding.network_apps.feature_conference.data.datasource

import com.survivalcoding.network_apps.feature_conference.domain.model.ConferenceItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConferenceDataSource @Inject constructor(private val conferenceService: ConferenceService) {
    suspend fun getData(): List<ConferenceItem>? {
        val response = conferenceService.getData()
        return if (response.isSuccessful) {
            response.body()
        } else {
            listOf()
        }
    }
}