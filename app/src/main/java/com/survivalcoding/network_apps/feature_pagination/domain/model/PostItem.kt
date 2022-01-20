package com.survivalcoding.network_apps.feature_pagination.domain.model

data class PostItem(
    val postContent: Post,
    val name: String,
) {
    val id: Int = postContent.id
}