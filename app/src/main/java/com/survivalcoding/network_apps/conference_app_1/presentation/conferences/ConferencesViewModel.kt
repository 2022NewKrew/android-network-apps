package com.survivalcoding.network_apps.conference_app_1.presentation.conferences

import androidx.lifecycle.*
import com.survivalcoding.network_apps.conference_app_1.domain.repository.ConferenceRepository
import com.survivalcoding.network_apps.conference_app_1.domain.usecase.GetConferencesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ListViewModel(
    private val getConferencesUseCase: GetConferencesUseCase,
) : ViewModel() {
    private var _state = MutableLiveData(ConferencesState())
    val state: LiveData<ConferencesState> = _state

    private var _exception: MutableLiveData<Throwable?> = MutableLiveData()
    val exception: LiveData<Throwable?> = _exception

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        getErrorMessage(throwable)
        _state.value = _state.value!!.copy(isLoading = false)
    }

    init {
        viewModelScope.launch(exceptionHandler) {
            _state.value = _state.value!!.copy(isLoading = true)
            _state.postValue(
                _state.value!!.copy(
                    conferences = getConferencesUseCase.invoke(),
                    isLoading = false
                )
            )
            _exception.value = null
        }
    }

    private fun getErrorMessage(throwable: Throwable) {
        throwable.printStackTrace()
        _exception.value = throwable
    }
}

class ListViewModelFactory(
    private val conferenceRepository: ConferenceRepository,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java))
            return ListViewModel(
                getConferencesUseCase = GetConferencesUseCase(conferenceRepository),
            ) as T
        else throw IllegalArgumentException()
    }
}