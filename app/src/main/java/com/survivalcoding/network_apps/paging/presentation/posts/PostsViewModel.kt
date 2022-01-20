package com.survivalcoding.network_apps.paging.presentation.posts.adapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.survivalcoding.network_apps.paging.domain.model.PostWithName
import com.survivalcoding.network_apps.paging.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class PostViewModel(repository: PostRepository) : ViewModel() {
    var posts: Flow<PagingData<PostWithName>> =
        repository.getResultStream().cachedIn(viewModelScope)
}

class PostViewModelFactory(
    private val repository: PostRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(repository) as T
    }
}