package com.survivalcoding.network_apps.conference.presentation

import androidx.lifecycle.*
import com.survivalcoding.network_apps.conference.domain.repository.ConferenceRepository
import kotlinx.coroutines.launch

class ConferenceViewModel(
    private val conferenceRepository: ConferenceRepository
) : ViewModel() {

    private val _state = MutableLiveData(ConferenceState())
    val state: LiveData<ConferenceState> = _state

    init {
        viewModelScope.launch {
            _state.value = state.value!!.copy(isLoading = true)

            _state.value = state.value!!.copy(
                // 변경사항
                conferenceInfo  = conferenceRepository.getConferenceList()
            )

            _state.value = state.value!!.copy(isLoading = false)
        }
    }

}