package com.survivalcoding.network_apps.paging.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.survivalcoding.network_apps.paging.data.repository.PostRepositoryImpl
import com.survivalcoding.network_apps.paging.domain.model.PostWithName
import kotlinx.coroutines.flow.Flow

class PostViewModel(repository: PostRepositoryImpl) : ViewModel() {
    var posts: Flow<PagingData<PostWithName>> =
        repository.getResultStream().cachedIn(viewModelScope)

}

class PostViewModelFactory(
    private val repository: PostRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(repository) as T
    }
}