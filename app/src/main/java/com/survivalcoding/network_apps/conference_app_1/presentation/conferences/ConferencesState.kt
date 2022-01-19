package com.survivalcoding.network_apps.conference_app_1.presentation.conferences

import com.survivalcoding.network_apps.conference_app_1.domain.model.Conference

data class ConferencesState(
    val conferences: List<Conference>? = null,
    val isLoading: Boolean = false
)
