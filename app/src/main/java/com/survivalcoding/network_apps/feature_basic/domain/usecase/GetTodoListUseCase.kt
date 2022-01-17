package com.survivalcoding.network_apps.feature_basic.domain.usecase

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository

class GetTodoListUseCase(
    private val repository: TodoRepository,
) {
    suspend operator fun invoke(): List<Todo> {
        return repository.getAllTodo()
    }
}