package com.survivalcoding.network_apps.feature_basic.presentation.conference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.survivalcoding.network_apps.feature_basic.domain.repository.ConferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConferenceViewModel @Inject constructor(
    private val conferenceRepository: ConferenceRepository
) :
    ViewModel() {
    val conferences = liveData {
        emit(conferenceRepository.getData())
    }
}