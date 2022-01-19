package com.example.presentation.conferencedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.Conference

class ConferenceDetailViewModel : ViewModel() {
    private val _conference = MutableLiveData<Conference>()
    val conference: LiveData<Conference> = _conference

    fun setConference(conference: Conference) {
        _conference.value = conference
    }
}

@Suppress("UNCHECKED_CAST")
class ConferenceDetailViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConferenceDetailViewModel::class.java)) {
            return ConferenceDetailViewModel() as T
        } else {
            throw IllegalArgumentException()
        }
    }
}