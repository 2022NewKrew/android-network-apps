package com.survivalcoding.network_apps.paging.ui.posts

import androidx.paging.PagingData

data class PostsUiState(
    val postItems: PagingData<PostItem>
)
