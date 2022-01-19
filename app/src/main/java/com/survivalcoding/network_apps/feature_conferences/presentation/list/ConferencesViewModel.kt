package com.survivalcoding.network_apps.feature_conferences.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import com.survivalcoding.network_apps.feature_conferences.domain.usecase.BaseUseCase
import com.survivalcoding.network_apps.feature_conferences.domain.usecase.GetConferencesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ConferencesViewModel(
    private val getConferencesUseCase: GetConferencesUseCase,
) : ViewModel() {
    private val _conferences = MutableStateFlow<List<Conference>>(listOf())
    private val _isLoading = MutableStateFlow(true)
    private val _error: MutableStateFlow<Throwable?> = MutableStateFlow(null)

    val state = combine(_conferences, _isLoading) { conferences, isLoading ->
        ConferencesState(conferences, isLoading)
    }.asLiveData()
    val error = _error.asLiveData()

    init {
        viewModelScope.launch {
            getConferencesUseCase().handle(
                onSuccess = { data ->
                    _conferences.value = data
                    _isLoading.value = false
                },
                onError = { error ->
                    _error.value = error
                    true
                }
            )
        }
    }

    suspend fun <T> BaseUseCase.Result<T>.handle(
        onError: ((Throwable) -> Boolean)? = null,
        onSuccess: suspend ((T) -> Unit),
    ) {
        when (this) {
            is BaseUseCase.Result.Success -> onSuccess(data)
            is BaseUseCase.Result.Error -> {
                onError?.invoke(error)
            }
        }
    }
}