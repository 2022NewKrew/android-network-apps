package com.survivalcoding.network_apps.conferences.domain.model

data class Conference(
    val end: String,
    val link: String,
    val location: String,
    val name: String,
    val start: String
)