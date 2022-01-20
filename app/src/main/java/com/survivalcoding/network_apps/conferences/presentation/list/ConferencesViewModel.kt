package com.survivalcoding.network_apps.conferences.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.conferences.domain.model.Conference
import com.survivalcoding.network_apps.conferences.domain.usecase.GetConferencesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ConferencesViewModel(
    private val getConferencesUseCase: GetConferencesUseCase,
) : ViewModel() {
    private val _conferences = MutableStateFlow<List<Conference>>(listOf())
    private val _isLoading = MutableStateFlow(true)

    val state = combine(_conferences, _isLoading) { conferences, isLoading ->
        ConferencesState(conferences, isLoading)
    }.asLiveData()

    init {
        viewModelScope.launch {
            _conferences.value = getConferencesUseCase()
            _isLoading.value = false
        }
    }
}