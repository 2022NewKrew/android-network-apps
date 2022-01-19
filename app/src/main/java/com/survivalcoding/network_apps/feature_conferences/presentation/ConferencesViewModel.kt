package com.survivalcoding.network_apps.feature_conferences.presentation

import androidx.lifecycle.*
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import com.survivalcoding.network_apps.feature_conferences.domain.repository.ConferenceRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ConferencesViewModel(private val repository: ConferenceRepository) : ViewModel() {
    private val _state = MutableLiveData(ConferencesState())
    val state: LiveData<ConferencesState> get() = _state

    init {
        getConferenceList()
    }

    private fun getConferenceList() = viewModelScope.launch {
        _state.value = state.value?.copy(isLoading = true)
        repository.getConferenceList().collect { list ->
            _state.value = state.value?.copy(conferences = list)
        }
        _state.value = state.value?.copy(isLoading = false)
    }

    fun selectConference(conference: Conference?) {
        _state.value = state.value?.copy(conference = conference)
    }
}