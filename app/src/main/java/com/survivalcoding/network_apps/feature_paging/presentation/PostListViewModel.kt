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

    private var lastPage = FIRST_PAGE_INDEX
    private var lastLoadedCount = -1

    private val _postListUiState =
        MutableStateFlow(PostListUiState(listOf(), isLoading = true, isError = false))
    val postListUiState = _postListUiState.asStateFlow()

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (lastLoadedCount == 0) {
            _postListUiState.value = _postListUiState.value.copy(isLoading = false)
            return
        }

        viewModelScope.launch {
            _postListUiState.value = _postListUiState.value.copy(isLoading = true, isError = false)

            try {
                val postsAndWriters = getPostAndWriterUseCase(lastPage)
                val newPostItemList = _postListUiState.value.postList.plus(postsAndWriters.map {
                    PostItem(it.first, it.second)
                })

                lastPage += 1
                lastLoadedCount = postsAndWriters.size
                _postListUiState.value = _postListUiState.value.copy(
                    postList = newPostItemList,
                    isLoading = lastLoadedCount != 0
                )
            } catch (httpException: HttpException) {
                if (BuildConfig.DEBUG) {
                    httpException.printStackTrace()
                }
                _postListUiState.value = _postListUiState.value.copy(isError = true, isLoading = false)
            } catch (exception: Exception) {
                if (BuildConfig.DEBUG) {
                    exception.printStackTrace()
                }
                _postListUiState.value = _postListUiState.value.copy(isError = true, isLoading = false)
            }
        }
    }

    data class PostListUiState(
        val postList: List<PostItem>,
        val isLoading: Boolean,
        val isError: Boolean
    )

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}

data class PostItem(
    val post: Post,
    val user: User
)