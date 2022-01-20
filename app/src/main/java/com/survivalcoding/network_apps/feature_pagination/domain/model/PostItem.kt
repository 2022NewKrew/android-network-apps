package com.survivalcoding.network_apps.feature_pagination.domain.model

data class PostItem(
    val postContent: Post,
    val name: String,
    val isFolded: Boolean = true,
) {
    val id: Int = postContent.id
}