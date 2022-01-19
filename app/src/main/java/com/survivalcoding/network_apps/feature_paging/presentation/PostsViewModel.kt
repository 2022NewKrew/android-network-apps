package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PostsViewModel(getPostsUseCase: GetPostsUseCase) : ViewModel() {
    private val _state = MutableLiveData(PostsState())
    val state: LiveData<PostsState> get() = _state
    val posts: Flow<PagingData<Post>> = getPostsUseCase().cachedIn(viewModelScope)

    init {
        getPosts()
    }

    private fun getPosts() = viewModelScope.launch {
        _state.value = state.value?.copy(isLoading = true)
        _state.value = state.value?.copy(isLoading = false)
    }
}