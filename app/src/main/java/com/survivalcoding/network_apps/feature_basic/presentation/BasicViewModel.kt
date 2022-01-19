package com.survivalcoding.network_apps.feature_basic.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import kotlinx.coroutines.launch

class BasicViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _state = MutableLiveData(BasicState())
    val state: LiveData<BasicState> = _state

    init {
        viewModelScope.launch {
            _state.value = state.value!!.copy(isLoading = true)
            _state.postValue(
                _state.value!!.copy(
                    todo = todoRepository.getTodoById(1),
                    isLoading = false
                )
            )
        }
    }
}