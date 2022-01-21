package com.survivalcoding.network_apps.paging.domain.model

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    val userName: String? = null
)