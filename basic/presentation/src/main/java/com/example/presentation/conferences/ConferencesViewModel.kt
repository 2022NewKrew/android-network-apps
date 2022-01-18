package com.example.presentation.conferences

import androidx.lifecycle.*
import com.example.domain.model.Conference
import com.example.domain.usecase.GetConferencesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConferencesViewModel(private val getConferencesUseCase: GetConferencesUseCase) : ViewModel() {
    private val _conferencesList = MutableLiveData<List<Conference>>()
    val conferenceList: LiveData<List<Conference>> = _conferencesList

    init {
        viewModelScope.launch {
            val newConferencesList = withContext(Dispatchers.IO) {
                getConferencesUseCase.invoke()
            }
            _conferencesList.value = newConferencesList
        }
    }
}

@Suppress("UNCHECKED_CAST")
class ConferencesViewModelFactory(private val getConferencesUseCase: GetConferencesUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConferencesViewModel::class.java)) {
            return ConferencesViewModel(getConferencesUseCase) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}