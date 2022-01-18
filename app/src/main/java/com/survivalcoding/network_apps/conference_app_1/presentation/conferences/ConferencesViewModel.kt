package com.survivalcoding.network_apps.conference_app_1.presentation.conferences

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.survivalcoding.network_apps.conference_app_1.domain.repository.ConferenceRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

class ListViewModel(
    private val conferenceRepository: ConferenceRepository,
    private val application: Application
) : ViewModel() {
    private var _state = MutableLiveData(ConferencesState())
    val state: LiveData<ConferencesState> = _state

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            getErrorMessage(throwable)
            _state.value = _state.value!!.copy(isLoading = false)
        }) {
            _state.value = _state.value!!.copy(isLoading = true)
            _state.postValue(
                _state.value!!.copy(
                    conferences = conferenceRepository.getConferences(),
                    isLoading = false
                )
            )
        }
    }

    private fun getErrorMessage(throwable: Throwable) {
        throwable.printStackTrace()
        when (throwable) {
            is SocketException -> Toast.makeText(
                application,
                "Socket Excpetion",
                Toast.LENGTH_SHORT
            ).show()
            is HttpException -> Toast.makeText(application, "Parse Error", Toast.LENGTH_SHORT)
                .show()
            is UnknownHostException -> Toast.makeText(
                application,
                "Wrong Connection",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}


class ListViewModelFactory(
    private val application: Application,
    private val conferenceRepository: ConferenceRepository,
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java))
            return ListViewModel(
                application = application,
                conferenceRepository = conferenceRepository,
            ) as T
        else throw IllegalArgumentException()
    }
}