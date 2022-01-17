package com.survivalcoding.network_apps.conference.domain.model

data class ConferenceInfo(
    val name: String,
    val link: String,
    val start: String,
    val end: String,
    val location: String
)