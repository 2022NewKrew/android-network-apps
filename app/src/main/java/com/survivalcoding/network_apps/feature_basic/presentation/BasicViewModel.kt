package com.survivalcoding.network_apps.feature_basic.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository

class BasicViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val todo = liveData {
        emit(todoRepository.getTodoById(1))
    }

}