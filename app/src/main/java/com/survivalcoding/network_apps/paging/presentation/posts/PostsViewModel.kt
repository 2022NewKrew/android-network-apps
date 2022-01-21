package com.survivalcoding.network_apps.paging.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.survivalcoding.network_apps.paging.domain.repository.PostRepository
import com.survivalcoding.network_apps.paging.domain.usecase.GetPostWithName
import com.survivalcoding.network_apps.paging.domain.usecase.GetRemoteUserById
import kotlinx.coroutines.flow.map

class PostViewModel(
    repository: PostRepository,
    getPostWithName: GetPostWithName
) : ViewModel() {
    private val userMap = mutableMapOf<Int, String>()
    val posts = repository.getPostStream().cachedIn(viewModelScope).map { posts ->
        posts.map { post ->
            getPostWithName.invoke(userMap, post)
        }
    }
}

class PostViewModelFactory(
    private val repository: PostRepository,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(
            repository = repository, getPostWithName = GetPostWithName(
                GetRemoteUserById(repository)
            )
        ) as T
    }
}