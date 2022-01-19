package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.*
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostsViewModel(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {
    private val _state = MutableLiveData(PostsState())
    val state: LiveData<PostsState> get() = _state

    init {
        getPosts()
    }

    private fun getPosts() = viewModelScope.launch {
        _state.value = state.value?.copy(isLoading = true)
        getPostsUseCase().collect { list ->
            _state.value = state.value?.copy(posts = list)
        }
        _state.value = state.value?.copy(isLoading = false)
    }
}