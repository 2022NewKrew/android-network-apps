package com.survivalcoding.network_apps.conference_app_1.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.survivalcoding.network_apps.conference_app_1.domain.repository.ConferenceRepository

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val conferenceRepository: ConferenceRepository
) : ViewModel() {

}