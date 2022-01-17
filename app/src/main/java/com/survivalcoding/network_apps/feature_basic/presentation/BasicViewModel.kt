package com.survivalcoding.network_apps.feature_basic.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.feature_basic.domain.model.Todo
import com.survivalcoding.network_apps.feature_basic.domain.usecase.GetTodoListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class BasicViewModel(
    private val getTodoListUseCase: GetTodoListUseCase
) : ViewModel() {
    private val _todoList = MutableStateFlow(listOf(Todo()))
    private val _todoNumber = MutableStateFlow(0)
    private val _isLoading = MutableStateFlow(true)

    val state = combine(_todoList, _todoNumber, _isLoading) { todoList, number, isLoading ->
        BasicState(todoList[number], isLoading)
    }.asLiveData()

    init {
        viewModelScope.launch {
            _todoList.value = getTodoListUseCase()
            _isLoading.value = false
        }
    }

    fun onEvent(event: BasicEvent) {
        when (event) {
            BasicEvent.MoveToFollowingTodo -> {
                _todoNumber.value = (_todoNumber.value + 1).coerceAtMost(_todoList.value.size)
            }
            is BasicEvent.MoveToInputNumberTodo -> {
                _todoNumber.value = event.num
            }
            BasicEvent.MoveToPreviousTodo -> {
                _todoNumber.value = (_todoNumber.value - 1).coerceAtLeast(0)
            }
        }
    }
}

sealed class BasicEvent {
    object MoveToPreviousTodo : BasicEvent()
    object MoveToFollowingTodo : BasicEvent()
    class MoveToInputNumberTodo(val num: Int) : BasicEvent()
}