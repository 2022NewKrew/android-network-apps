package com.survivalcoding.network_apps.conference_app_1.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.survivalcoding.network_apps.conference_app_1.domain.model.Conference
import com.survivalcoding.network_apps.conference_app_1.presentation.conferences.ConferencesFragment.Companion.CLICK

class DetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _conference: MutableLiveData<Conference> = MutableLiveData()
    val conference: LiveData<Conference> get() = _conference

    init {
        savedStateHandle.get<Conference>(CLICK)?.let {
            _conference.value = it
        }
    }
}