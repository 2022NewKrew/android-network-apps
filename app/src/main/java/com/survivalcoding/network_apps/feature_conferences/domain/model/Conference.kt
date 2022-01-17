package com.survivalcoding.network_apps.feature_conferences.domain.model

data class Conference(
    val name: String,
    val link: String,
    val start: String,
    val end: String,
    val location: String
)
