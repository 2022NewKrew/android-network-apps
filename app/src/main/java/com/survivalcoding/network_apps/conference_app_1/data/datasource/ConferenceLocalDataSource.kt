package com.survivalcoding.network_apps.conference_app_1.data.datasource

import com.survivalcoding.network_apps.conference_app_1.domain.model.Conference

class ConferenceLocalDataSource {
    private var conferenceList: List<Conference> = listOf()

    fun saveList(list: List<Conference>) {
        conferenceList = list
    }

    fun getLists(): List<Conference> = conferenceList
    fun getConference(name: String): Conference? = conferenceList.find { it.name == name }
}