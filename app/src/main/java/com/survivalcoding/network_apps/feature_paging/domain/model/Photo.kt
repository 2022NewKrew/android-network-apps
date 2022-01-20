package com.survivalcoding.network_apps.feature_paging.domain.model

data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)