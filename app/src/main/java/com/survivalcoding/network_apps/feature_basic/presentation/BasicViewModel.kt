package com.survivalcoding.network_apps.feature_basic.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.usecase.GetTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class BasicViewModel(
    private val getTodoUseCase: GetTodoUseCase,
) : ViewModel() {
    private val _todo = MutableStateFlow(
        Todo(
            completed = false,
            id = 0,
            title = "",
            userId = 0,
        )
    )
    private val _isLoading = MutableStateFlow(true)

    val state = combine(_todo, _isLoading) { todo, isLoading ->
        BasicState(todo, isLoading)
    }.asLiveData()

    init {
        viewModelScope.launch {
            val todo = getTodoUseCase(1)
            _todo.value = todo
            _isLoading.value = false
        }
    }
}