package com.survivalcoding.network_apps.paging.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.survivalcoding.network_apps.paging.data.repository.PostRepositoryImpl
import com.survivalcoding.network_apps.paging.domain.model.Post
import kotlinx.coroutines.flow.Flow

class PostViewModel(private val repository: PostRepositoryImpl) : ViewModel() {
    private var posts: Flow<PagingData<Post>>? =
        repository.getResultStream().cachedIn(viewModelScope)

}