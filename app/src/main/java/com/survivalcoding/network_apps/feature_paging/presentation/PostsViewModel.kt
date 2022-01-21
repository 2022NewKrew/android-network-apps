package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.Flow

class PostsViewModel(getPostsUseCase: GetPostsUseCase) : ViewModel() {
    val posts: Flow<PagingData<Post>> = getPostsUseCase().cachedIn(viewModelScope)
}