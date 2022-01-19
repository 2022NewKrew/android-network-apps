package com.survivalcoding.network_apps.feature_basic.presentation

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo

data class BasicState(
    val todo: Todo? = null,
    val isLoading: Boolean = false
)