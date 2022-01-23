package com.survivalcoding.network_apps.feature_pagination_v2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem
import com.survivalcoding.network_apps.feature_pagination_v2.domain.repository.PostRepository
import com.survivalcoding.network_apps.feature_pagination_v2.domain.usecase.Result
import com.survivalcoding.network_apps.feature_pagination_v2.domain.usecase.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class PaginationV2ViewModel(
    private val useCases: UseCases,
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Init)
    private val _posts = MutableStateFlow<List<Post>>(listOf())
    private val _userName = MutableStateFlow<Map<Int, String>>(mapOf())
    private val _folded = MutableStateFlow<Map<Int, Boolean>>(mapOf())

    private var targetPage = 1
    private var lastLoadedCount = -1
    private val pageSize = 20

    val postItem = combine(_posts, _userName, _folded) { posts, _, folded ->
        posts.map { post ->
            val name = getUserName(post.id)
            PostItem(post, name, folded[post.id] ?: false)
        }
    }.asLiveData()

    private fun getUserName(id: Int): String {
        viewModelScope.launch {
            val nameMap = _userName.value.toMutableMap()
            if (nameMap[id] == null) {
                when (val name = useCases.getUserByIdUseCase(id)) {
                    is Result.Error -> _state.value =
                        State.Error(LoadingError.UserLoadingError(name.error))
                    is Result.Success -> nameMap[id] = name.data.name
                }
            }
            _userName.value = nameMap
        }
        return _userName.value[id] ?: ""
    }

    fun loadPage() {
        if (lastLoadedCount == 0) {
            _state.value = State.EndLoading
            return
        }

        viewModelScope.launch {
            _state.value = State.Loading

            when (val loadPostsResult = useCases.getPostPageUseCase(targetPage, pageSize)) {
                is Result.Error -> _state.value =
                    State.Error(LoadingError.PostLoadingError(loadPostsResult.error))
                is Result.Success -> {
                    val loadedList = loadPostsResult.data
                    _posts.value = _posts.value.plus(loadedList)
                    lastLoadedCount = loadedList.size
                    _state.value = State.NotLoading
                }
            }
        }
    }
}

sealed class State {
    object Init : State()
    data class Error(val error: LoadingError) : State()
    object Loading : State()
    object NotLoading : State()
    object EndLoading : State()
}

sealed class LoadingError {
    data class UserLoadingError(val t: Throwable) : LoadingError()
    data class PostLoadingError(val t: Throwable) : LoadingError()
}

class PaginationV2ViewModelFactory(
    private val repository: PostRepository,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PaginationV2ViewModel::class.java)) {
            return PaginationV2ViewModel(
                UseCases(repository),
            ) as T
        }
        return super.create(modelClass)
    }
}