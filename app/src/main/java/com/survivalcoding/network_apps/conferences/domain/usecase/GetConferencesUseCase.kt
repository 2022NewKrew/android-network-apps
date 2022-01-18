package com.survivalcoding.network_apps.conferences.domain.usecase

import com.survivalcoding.network_apps.conferences.domain.model.Conference
import com.survivalcoding.network_apps.conferences.domain.repository.ConferenceRepository

class GetConferencesUseCase(
    private val repositoryImpl: ConferenceRepository
) {
    suspend operator fun invoke(): List<Conference> {
        return repositoryImpl.getConferences()
    }
}