package com.survivalcoding.network_apps.feature_pagination.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.PageRemoteNotPagingDataSource
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.PageRemotePostItemDataSource
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

    val flow = _posts

    init {
        viewModelScope.launch {
            _posts.value = repository.getPostItems()
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