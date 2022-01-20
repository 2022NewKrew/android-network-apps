package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.BuildConfig
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetPostsUseCase
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private var lastPage = FIRST_PAGE_INDEX
    private var lastLoadedCount = -1

    private val _postListUiState =
        MutableStateFlow(PostListUiState(listOf(), isLoading = true, isError = false))
    val postListUiState = _postListUiState.asStateFlow()

    init {
        loadNextPage(0)
    }

    fun loadNextPage(itemCount: Int) {
        if (lastLoadedCount == 0) {
            _postListUiState.value = _postListUiState.value.copy(isLoading = false)
            return
        }

        viewModelScope.launch {
            _postListUiState.value = _postListUiState.value.copy(isLoading = true, isError = false)

            val deferredPostWriters = mutableMapOf<Int, Deferred<User>>()

            try {
                val newPosts = getPostsUseCase(lastPage).filter {
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

                lastPage += 1
                lastLoadedCount = newPosts.size
                _postListUiState.value = _postListUiState.value.copy(postList = newPostItemList, isLoading = lastLoadedCount != 0)
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