package com.survivalcoding.network_apps.conference_app_1.presentation.list

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.survivalcoding.network_apps.conference_app_1.domain.model.Conference
import com.survivalcoding.network_apps.conference_app_1.domain.repository.ConferenceRepository
import kotlinx.coroutines.launch

class ListViewModel(
    private val conferenceRepository: ConferenceRepository,
    application: Application
) : AndroidViewModel(application) {
    private var _conferences = MutableLiveData<List<Conference>>()
    val conference: LiveData<List<Conference>> = _conferences

    init {
        try {
            viewModelScope.launch {
                _conferences.postValue(conferenceRepository.getConferences())
            }
        } catch (e: Exception) {
            Toast.makeText(application, e.toString(), Toast.LENGTH_SHORT).show()
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