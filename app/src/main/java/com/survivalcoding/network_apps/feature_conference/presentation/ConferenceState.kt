package com.survivalcoding.network_apps.feature_conference.presentation

import com.survivalcoding.network_apps.feature_conference.domain.model.ConferenceInfo

// 변경사항
data class ConferenceState(
    val conferenceInfo: List<ConferenceInfo>? = null,
    val isLoading: Boolean = false
)