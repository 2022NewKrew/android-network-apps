package com.survivalcoding.network_apps.conferences.presentation.list

import com.survivalcoding.network_apps.conferences.domain.model.Conference

data class ConferencesState(
    val conferences: List<Conference>,
    val isLoading: Boolean,
)