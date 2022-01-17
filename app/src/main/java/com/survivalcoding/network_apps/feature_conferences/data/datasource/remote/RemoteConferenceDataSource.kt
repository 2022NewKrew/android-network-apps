package com.survivalcoding.network_apps.feature_conferences.data.datasource.remote

import com.survivalcoding.network_apps.feature_conferences.data.datasource.ConferenceDataSource
import com.survivalcoding.network_apps.feature_conferences.data.datasource.remote.service.ConferenceService
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference

class RemoteConferenceDataSource : ConferenceDataSource {
    override suspend fun getConferenceList(): List<Conference> =
        RetrofitClient.getClient().create(ConferenceService::class.java).getConferenceList()
}