package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetPostsUseCase
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private var lastPage = FIRST_PAGE_INDEX

    private val _postListUiState =
        MutableStateFlow(PostListUiState(listOf(), true))
    val postListUiState = _postListUiState.asStateFlow()

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        viewModelScope.launch {
            _postListUiState.value = _postListUiState.value.copy(isLoading = true)

            val newPostList: List<PostItem> = postListUiState.value
                .postList
                .plus(
                    getPostsUseCase(++lastPage).filter {
                        it.userId != null
                    }.map {
                        PostItem(it, getUserUseCase(it.userId ?: 0))
                    }
                )

            _postListUiState.value = _postListUiState.value.copy(
                postList = newPostList, isLoading = false
            )
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 0
    }
}

data class PostItem(
    val post: Post,
    val user: User
)

data class PostListUiState(
    val postList: List<PostItem>,
    val isLoading: Boolean
)