package com.survivalcoding.network_apps.feature_conferences.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import com.survivalcoding.network_apps.feature_conferences.domain.repository.ConferenceRepository
import kotlinx.coroutines.launch

class ConferencesViewModel(private val repository: ConferenceRepository) : ViewModel() {
    private val _conferences = MutableLiveData<List<Conference>>()
    val conferences get() = _conferences
    private val _isLoading = MutableLiveData(false)
    val isLoading get() = _isLoading
    private val _conferenceSelected = MutableLiveData<Conference?>()
    val conferenceSelected get() = _conferenceSelected

    init {
        getConferenceList()
    }

    private fun getConferenceList() = viewModelScope.launch {
        _isLoading.value = true
        _conferences.value = repository.getConferenceList()
        _isLoading.value = false
    }

    fun selectConference(conference: Conference?) {
        _conferenceSelected.value = conference
    }
}