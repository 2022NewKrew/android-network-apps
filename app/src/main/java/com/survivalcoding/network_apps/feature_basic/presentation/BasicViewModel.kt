package com.survivalcoding.network_apps.feature_basic.presentation

import androidx.lifecycle.*
import com.survivalcoding.network_apps.feature_basic.core.Result
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class BasicViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _state = MutableLiveData(BasicState())
    val state: LiveData<BasicState> = _state

    private val _event = MutableSharedFlow<UiEvent>()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            _state.value = state.value!!.copy(isLoading = true)

            when (val result = todoRepository.getTodoById(1)) {
                is Result.Success -> {
                    _state.value = state.value!!.copy(
                        todo = result.data,
                        isLoading = false
                    )
                }
                is Result.Error -> {
                    _state.value = state.value!!.copy(isLoading = false)
                    _event.emit(UiEvent.ShowSnackBar(result.message))
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
    }
}