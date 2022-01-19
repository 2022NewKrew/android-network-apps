package com.survivalcoding.network_apps.feature_paging.presentation

import com.survivalcoding.network_apps.feature_paging.domain.model.Post

data class PostsState(
    val posts: List<Post>? = null,
    val isLoading: Boolean = false
)
