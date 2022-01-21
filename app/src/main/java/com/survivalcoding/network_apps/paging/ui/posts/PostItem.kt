package com.survivalcoding.network_apps.paging.ui.posts

import com.survivalcoding.network_apps.paging.domain.model.Post

data class PostItem(
    val post: Post,
    val userName: String,
    val isExpanded: Boolean
)
