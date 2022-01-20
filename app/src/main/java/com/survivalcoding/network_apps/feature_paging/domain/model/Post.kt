package com.survivalcoding.network_apps.feature_paging.domain.model

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    var username: String = ""
)
