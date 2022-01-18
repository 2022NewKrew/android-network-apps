package com.example.presentation.conferences

import androidx.lifecycle.*
import com.example.domain.model.Conference
import com.example.domain.usecase.GetConferencesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConferencesViewModel(private val getConferencesUseCase: GetConferencesUseCase) : ViewModel() {
    private val _conferenceUiState = MutableLiveData(ConferencesUiState(listOf(), true))
    val conferenceUiState: LiveData<ConferencesUiState> = _conferenceUiState

    init {
        loadConferences()
    }

    private fun loadConferences() {
        viewModelScope.launch {
            val newConferencesList = withContext(Dispatchers.IO) {
                getConferencesUseCase.invoke()
            }
            _conferenceUiState.value = ConferencesUiState(newConferencesList, false)
        }
    }
}

class ConferencesUiState(
    val conferenceList: List<Conference>,
    val isLoading: Boolean
)

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