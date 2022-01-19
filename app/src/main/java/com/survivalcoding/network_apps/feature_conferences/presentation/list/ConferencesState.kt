package com.survivalcoding.network_apps.feature_conferences.presentation.list

import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference

data class ConferencesState(
    val conferences: List<Conference>,
    val isLoading: Boolean,
)