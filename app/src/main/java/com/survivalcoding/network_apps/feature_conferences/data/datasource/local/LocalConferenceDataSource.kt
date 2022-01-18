package com.survivalcoding.network_apps.feature_conferences.data.datasource.local

import com.survivalcoding.network_apps.feature_conferences.data.datasource.ConferenceDataSource
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LocalConferenceDataSource : ConferenceDataSource {
    override suspend fun getConferenceList(): Flow<List<Conference>> {
        delay(1000)
        return flowOf(
            listOf(
                Conference(
                    name = "SwiftLeeds",
                    link = "https://swiftleeds.co.uk/",
                    start = "2020-10-07",
                    end = "2020-10-08",
                    location = "🇬🇧 Leeds, UK"
                ),
                Conference(
                    name = "MobOS",
                    link = "http://romobos.com/",
                    start = "2020-02-20",
                    end = "2020-02-21",
                    location = "🇷🇴 Cluj-Napoca, Romania"
                ),
                Conference(
                    name = "dot Swift",
                    link = "http://www.dotswift.io",
                    start = "2020-02-03",
                    end = "2020-02-21",
                    location = "🇫🇷 Paris, France"
                ),
            )
        )
    }
}