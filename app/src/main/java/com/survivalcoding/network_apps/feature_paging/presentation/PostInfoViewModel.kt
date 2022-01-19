package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import com.survivalcoding.network_apps.feature_paging.domain.repository.UserRepository
import kotlinx.coroutines.launch

class PostInfoViewModel(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableLiveData(PostInfoState())
    val state: LiveData<PostInfoState> = _state

    val posts = postRepository.getPosts().cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            _state.value = state.value!!.copy(isLoading = true)
            _state.value = state.value!!.copy(isLoading = false)
        }
    }

}