package com.survivalcoding.network_apps.feature_basic.presentation

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo

data class BasicState(
    val todoList: List<Todo>,
    val isLoading: Boolean,
    val number: Int,
) {
    val todo = todoList[number]
}