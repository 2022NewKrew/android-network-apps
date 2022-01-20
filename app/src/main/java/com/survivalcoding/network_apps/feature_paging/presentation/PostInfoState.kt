package com.survivalcoding.network_apps.feature_paging.presentation

import com.survivalcoding.network_apps.feature_paging.domain.model.Post


data class PostInfoState(
    val post: List<Post>? = null,
    val isLoading: Boolean = false,
    val page: Int = 1
)