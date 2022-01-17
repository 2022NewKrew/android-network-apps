package com.example.domain.repository

import com.example.domain.model.Conference

interface ConferenceRepository {
    suspend fun getConferences(): List<Conference>
}