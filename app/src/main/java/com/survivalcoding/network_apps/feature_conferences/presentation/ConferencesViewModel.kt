package com.survivalcoding.network_apps.feature_conferences.presentation

import androidx.lifecycle.*
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import com.survivalcoding.network_apps.feature_conferences.domain.repository.ConferenceRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ConferencesViewModel(private val repository: ConferenceRepository) : ViewModel() {
    private val _conferences = MutableLiveData<List<Conference>>()
    val conferences: LiveData<List<Conference>> get() = _conferences
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _conferenceSelected = MutableLiveData<Conference?>()
    val conferenceSelected: LiveData<Conference?> get() = _conferenceSelected

    init {
        getConferenceList()
    }

    private fun getConferenceList() = viewModelScope.launch {
        _isLoading.value = true
        repository.getConferenceList().collect { list ->
            _conferences.value = list
        }
        _isLoading.value = false
    }

    fun selectConference(conference: Conference?) {
        _conferenceSelected.value = conference
    }
}