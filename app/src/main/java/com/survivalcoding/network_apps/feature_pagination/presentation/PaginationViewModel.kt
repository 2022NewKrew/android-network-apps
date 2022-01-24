package com.survivalcoding.network_apps.feature_pagination.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem
import com.survivalcoding.network_apps.feature_pagination.domain.repository.PostItemRepository
import com.survivalcoding.network_apps.feature_pagination.domain.usecase.BaseUseCase
import com.survivalcoding.network_apps.feature_pagination.domain.usecase.GetPostsUseCase
import com.survivalcoding.network_apps.feature_pagination.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PaginationViewModel(
    getPostsUseCase: GetPostsUseCase,
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {
    private val _posts = getPostsUseCase().cachedIn(viewModelScope)
    private val _userData = MutableStateFlow<Map<Int, String>>(mapOf())
    private val _isFolded = MutableStateFlow<Map<Int, Boolean>>(mapOf())
    private val _state = MutableStateFlow<PaginationState>(PaginationState.NotLoading)

    val postItems = combine(_posts, _userData, _isFolded) { posts, userData, isFolded ->
        posts.map { post ->
            val name = userData[post.userId] ?: ""
            val folded = isFolded[post.id] ?: false
            PostItem(post, name, folded)
        }
    }
    val state = _state.asLiveData()

    init {
        loadUser()
    }

    fun loadUser() = viewModelScope.launch {
        val map = mutableMapOf<Int, String>()
        when (val result = getUsersUseCase()) {
            is BaseUseCase.Result.Error -> _state.value = PaginationState.UserLoadingError
            is BaseUseCase.Result.Success -> {
                result.data.forEach { user -> map[user.id] = user.username }
                _userData.value = map
            }
        }
    }

    fun setState(state: PaginationState) {
        if (state is PaginationState.PostLoadingError) {
            if (_state.value !is PaginationState.UserLoadingError) _state.value = state
        } else _state.value = state
    }

    fun expandPost(postItem: PostItem) {
        val map = _isFolded.value.toMutableMap()
        map[postItem.id] =
            when (map[postItem.id]) {
                true -> false
                else -> true
            }
        _isFolded.value = map
    }
}

sealed class PaginationState {
    object UserLoadingError : PaginationState()
    object PostLoadingError : PaginationState()
    object NotLoading : PaginationState()
    object Loading : PaginationState()
    object EndLoading : PaginationState()
}

class PaginationViewModelFactory(
    private val repository: PostItemRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PaginationViewModel::class.java)) {
            return PaginationViewModel(
                GetPostsUseCase(repository),
                GetUsersUseCase(repository),
            ) as T
        }
        return super.create(modelClass)
    }
}