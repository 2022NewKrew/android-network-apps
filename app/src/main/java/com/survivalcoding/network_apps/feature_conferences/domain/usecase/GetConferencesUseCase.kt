package com.survivalcoding.network_apps.feature_conferences.domain.usecase

import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import com.survivalcoding.network_apps.feature_conferences.domain.repository.ConferenceRepository

class GetConferencesUseCase(
    private val repositoryImpl: ConferenceRepository
) : BaseUseCase() {
    suspend operator fun invoke(): Result<List<Conference>> {
        return result { repositoryImpl.getConferences() }
    }
}