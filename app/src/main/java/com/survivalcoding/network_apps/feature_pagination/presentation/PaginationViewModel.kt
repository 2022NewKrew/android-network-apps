package com.survivalcoding.network_apps.feature_pagination.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.PageRemoteNotPagingDataSource
import com.survivalcoding.network_apps.feature_pagination.data.repository.PostItemsNotPageRepository
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem
import com.survivalcoding.network_apps.feature_pagination.domain.repository.PostItemRepository
import com.survivalcoding.network_apps.feature_pagination.domain.usecase.BaseUseCase
import com.survivalcoding.network_apps.feature_pagination.domain.usecase.GetPostItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PaginationViewModel(
    private val getPostItemsUseCase: GetPostItemsUseCase,
    private val repository: PostItemsNotPageRepository,
) : ViewModel() {
    private val _error = MutableStateFlow<String?>(null)
    private val _posts = MutableStateFlow<List<PostItem>>(listOf())

    private val _p = MutableStateFlow<PagingData<PostItem>>(PagingData.empty())

    val flow = _posts
    val p: StateFlow<PagingData<PostItem>> = _p

    init {
        viewModelScope.launch {
            _posts.value = repository.getPostItems()

            when (val result = getPostItemsUseCase()) {
                is BaseUseCase.Result.Error -> _error.value = result.error.message
                is BaseUseCase.Result.Success -> {
                    _p.value = result.data.stateIn(viewModelScope).value
                }
            }
        }
    }

    private suspend fun <T> BaseUseCase.Result<T>.handle(
        onError: ((Throwable) -> Boolean)? = null,
        onSuccess: suspend ((T) -> Unit)
    ) {
        when (this) {
            is BaseUseCase.Result.Error -> if (onError == null) {
                _error.value = error.message
            } else {
                onError(error)
            }
            is BaseUseCase.Result.Success -> onSuccess(data)
        }
    }
}

class PaginationViewModelFactory(
    private val repository: PostItemRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PaginationViewModel::class.java)) {
            return PaginationViewModel(
                GetPostItemsUseCase(repository),
                PostItemsNotPageRepository(PageRemoteNotPagingDataSource())
            ) as T
        }
        return super.create(modelClass)
    }
}