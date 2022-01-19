package com.survivalcoding.network_apps.feature_conference_app_1.ui.conferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.survivalcoding.network_apps.feature_conference_app_1.domain.repository.ConferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConferencesViewModel @Inject constructor(private val conferenceRepository: ConferenceRepository): ViewModel() {

    val conferences = liveData {
        emit(conferenceRepository.getConferences())
    }
}