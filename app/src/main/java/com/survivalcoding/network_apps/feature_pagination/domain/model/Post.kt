package com.survivalcoding.network_apps.feature_pagination.domain.model

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)