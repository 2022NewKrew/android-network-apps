package com.example.domain.usecase

import com.example.domain.repository.ConferenceRepository

class GetConferencesUseCase(private val conferenceRepository: ConferenceRepository) {
    suspend operator fun invoke() = conferenceRepository.getConferences()
}