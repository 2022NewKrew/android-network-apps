package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import kotlinx.coroutines.flow.Flow


data class PostInfoState(
    val post: Flow<PagingData<Post>>? = null,
    val users: MutableMap<Int, User> = mutableMapOf(),
    val isLoading: Boolean = false,
    val page: Int = 1
)