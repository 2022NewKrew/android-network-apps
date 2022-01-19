package com.survivalcoding.network_apps.feature_basic.domain.model

data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)

fun emptyTodo(): Todo =
    Todo(
        id = 0,
        title = "",
        userId = 0,
        completed = false
    )