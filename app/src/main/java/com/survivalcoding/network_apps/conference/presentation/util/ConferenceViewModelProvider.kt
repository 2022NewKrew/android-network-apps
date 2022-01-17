package com.survivalcoding.network_apps.conference.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.network_apps.conference.domain.repository.ConferenceRepository
import com.survivalcoding.network_apps.conference.presentation.ConferenceViewModel

class ConferenceViewModelProvider(
    private val repository: ConferenceRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConferenceViewModel::class.java)) {
            return ConferenceViewModel(
                repository
            ) as T
        }
        return super.create(modelClass)
    }
}