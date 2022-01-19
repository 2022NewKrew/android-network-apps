package com.survivalcoding.network_apps.feature_basic.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasicViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {
    val todo = liveData {
        emit(todoRepository.getTodoById(1))
    }
}