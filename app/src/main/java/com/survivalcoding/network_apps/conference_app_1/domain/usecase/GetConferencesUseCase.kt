package com.survivalcoding.network_apps.conference_app_1.domain.usecase

import com.survivalcoding.network_apps.conference_app_1.domain.model.Conference
import com.survivalcoding.network_apps.conference_app_1.domain.repository.ConferenceRepository

class GetConferencesUseCase(private val repository: ConferenceRepository) {
    suspend operator fun invoke(): List<Conference> {
        return repository.getConferences()
    }
}