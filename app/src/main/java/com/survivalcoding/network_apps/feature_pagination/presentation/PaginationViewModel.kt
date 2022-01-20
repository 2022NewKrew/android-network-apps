package com.survivalcoding.network_apps.feature_pagination.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.PageRemoteNotPagingDataSource
import com.survivalcoding.network_apps.feature_pagination.data.repository.PostItemsNotPageRepository
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem
import com.survivalcoding.network_apps.feature_pagination.domain.repository.PostItemRepository
import com.survivalcoding.network_apps.feature_pagination.domain.usecase.BaseUseCase
import com.survivalcoding.network_apps.feature_pagination.domain.usecase.GetPostsUseCase
import com.survivalcoding.network_apps.feature_pagination.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class PaginationViewModel(
    getPostsUseCase: GetPostsUseCase,
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {
    private val _posts = getPostsUseCase().cachedIn(viewModelScope)
    private val _userData = MutableStateFlow<Map<Int, String>>(mapOf())

    val state = combine(_posts, _userData) { posts, userData ->
        posts.map { post ->
            val name = userData[post.userId] ?: ""
            PostItem(post, name)
        }
    }

    init {
        viewModelScope.launch {
            val map = mutableMapOf<Int, String>()
            getUsersUseCase().forEach { user -> map[user.id] = user.username }
            _userData.value = map
        }
    }
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