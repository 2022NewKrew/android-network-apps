package com.survivalcoding.network_apps.feature_paging.presentation

import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User

data class PostWithUserInfo(
    val post: Post,
    val user: User?
)