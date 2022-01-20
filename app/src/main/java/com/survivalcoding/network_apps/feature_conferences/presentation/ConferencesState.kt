package com.survivalcoding.network_apps.feature_conferences.presentation

import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference

data class ConferencesState(
    val conferences: List<Conference>? = null,
    val conference: Conference? = null,
    val isLoading: Boolean = false
)