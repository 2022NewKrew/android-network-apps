package com.survivalcoding.network_apps.feature_basic.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import com.survivalcoding.network_apps.feature_basic.network.TodosApi
import kotlinx.coroutines.launch

class BasicViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _state = MutableLiveData(BasicState())
    val state: LiveData<BasicState> = _state

    init {
        viewModelScope.launch {
            _state.value = state.value!!.copy(isLoading = true)

            try {
                val response = TodosApi.retrofitService.getTodo(1)
                _state.value = _state.value!!.copy(
                    todo = response.body()
                )
            } catch (e: Exception) { // Exception handling이 필요
                Log.d("viewModelException", e.toString())

                _state.value = state.value!!.copy(
                    todo = todoRepository.getTodoById(2)
                )
            }


            _state.value = state.value!!.copy(isLoading = false)
        }
    }

}