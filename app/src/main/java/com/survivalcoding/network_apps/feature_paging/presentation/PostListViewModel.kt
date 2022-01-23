package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.BuildConfig
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetPostAndWriterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostAndWriterUseCase: GetPostAndWriterUseCase
) : ViewModel() {

    private var lastLoadedCount = -1

    private val _postListUiState =
        MutableStateFlow(PostListUiState(listOf(), isLoading = true, isError = false))
    val postListUiState = _postListUiState.asStateFlow()

    init {
        loadPage(FIRST_PAGE_INDEX)
    }

    fun loadPage(page: Int) {
        if (lastLoadedCount == 0) {
            _postListUiState.value = _postListUiState.value.copy(isLoading = false)
            return
        }

        viewModelScope.launch {
            _postListUiState.value = _postListUiState.value.copy(isLoading = true, isError = false)

            try {
                val postsAndWriters = getPostAndWriterUseCase(page, PAGE_SIZE, viewModelScope)
                val newPostItemList = _postListUiState.value.postList.plus(postsAndWriters.map {
                    PostItem(it.first, it.second)
                })

                lastLoadedCount = postsAndWriters.size
                _postListUiState.value = _postListUiState.value.copy(
                    postList = newPostItemList,
                    isLoading = lastLoadedCount == PAGE_SIZE
                )
            } catch (httpException: HttpException) {
                if (BuildConfig.DEBUG) {
                    httpException.printStackTrace()
                }
                _postListUiState.value =
                    _postListUiState.value.copy(isError = true, isLoading = false)
            } catch (exception: Exception) {
                if (BuildConfig.DEBUG) {
                    exception.printStackTrace()
                }
                _postListUiState.value =
                    _postListUiState.value.copy(isError = true, isLoading = false)
            }
        }
    }

    fun toggleFoldedState(postId: Int) {
        _postListUiState.value =
            _postListUiState.value.copy(
                postList = _postListUiState.value.postList.map {
                    if (it.post.id == postId) it.copy(isFolded = !it.isFolded)
                    else it
                }.toMutableList()
            )
    }

    data class PostListUiState(
        val postList: List<PostItem>,
        val isLoading: Boolean,
        val isError: Boolean
    )

    companion object {
        const val FIRST_PAGE_INDEX = 1
        const val PAGE_SIZE = 30
    }
}

data class PostItem(
    val post: Post,
    val user: User,
    val isFolded: Boolean = true
)