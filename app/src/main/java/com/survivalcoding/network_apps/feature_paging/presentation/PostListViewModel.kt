package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetPostsUseCase
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private var lastPage = FIRST_PAGE_INDEX
    private var prevListItemCount = 0

    private val _postListUiState =
        MutableStateFlow(PostListUiState(listOf(), true))
    val postListUiState = _postListUiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        loadNextPage(0)
    }

    fun loadNextPage(itemCount: Int) {
        if (0 < prevListItemCount && prevListItemCount == itemCount) {
            sendEvent(Event.NoMoreDataEvent)
            return
        }
        prevListItemCount = itemCount

        viewModelScope.launch {
            _postListUiState.value = _postListUiState.value.copy(isLoading = true)

            val deferredPostWriters = mutableMapOf<Int, Deferred<User>>()

            val newPosts = getPostsUseCase(++lastPage).filter {
                it.userId != null
            }

            newPosts.forEach {
                deferredPostWriters[it.id ?: 0] = async {
                    getUserUseCase(it.userId ?: 0)
                }
            }

            val newPostItemList: List<PostItem> = postListUiState.value
                .postList
                .plus(
                    newPosts.filter {
                        deferredPostWriters[it.id] != null
                    }.map {
                        PostItem(it, deferredPostWriters[it.id]?.await() ?: User())
                    }
                )

            _postListUiState.value = _postListUiState.value.copy(
                postList = newPostItemList, isLoading = false
            )
        }
    }

    fun sendEvent(event: Event) {
        viewModelScope.launch { _eventFlow.emit(event) }
    }

    data class PostListUiState(
        val postList: List<PostItem>,
        val isLoading: Boolean
    )

    sealed class Event {
        object NoMoreDataEvent : Event()
        object NetworkErrorEvent : Event()
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 0
    }
}

data class PostItem(
    val post: Post,
    val user: User
)