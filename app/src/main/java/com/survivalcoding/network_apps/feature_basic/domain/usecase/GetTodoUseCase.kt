package com.survivalcoding.network_apps.feature_basic.domain.usecase

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository

class GetTodoUseCase(
    private val repository: TodoRepository,
) {
    suspend operator fun invoke(id: Int): Todo {
        return repository.getTodoById(id)
    }
}