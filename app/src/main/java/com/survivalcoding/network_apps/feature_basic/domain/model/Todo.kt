package com.survivalcoding.network_apps.feature_basic.domain.model

data class Todo(
    val completed: Boolean = false,
    val id: Int = 0,
    val title: String = "",
    val userId: Int = 0,
)

class TodoContainer : ArrayList<Todo>()