package com.survivalcoding.network_apps.feature_conferences.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.network_apps.feature_conferences.domain.repository.ConferenceRepository
import com.survivalcoding.network_apps.feature_conferences.domain.usecase.GetConferencesUseCase
import com.survivalcoding.network_apps.feature_conferences.presentation.list.ConferencesViewModel

class ConferencesViewModelProvider(
    private val repository: ConferenceRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConferencesViewModel::class.java)) {
            return ConferencesViewModel(
                GetConferencesUseCase(repository)
            ) as T
        }
        return super.create(modelClass)
    }
}