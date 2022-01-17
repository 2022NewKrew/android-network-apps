package com.survivalcoding.network_apps.feature_conferences.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.network_apps.feature_conferences.domain.repository.ConferenceRepository

class ConferencesViewModelFactory(private val repository: ConferenceRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConferencesViewModel::class.java)) {
            return ConferencesViewModel(repository) as T
        }
        return super.create(modelClass)
    }
}