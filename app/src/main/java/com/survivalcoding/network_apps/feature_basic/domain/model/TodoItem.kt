package com.survivalcoding.network_apps.feature_basic.domain.model

data class TodoItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)