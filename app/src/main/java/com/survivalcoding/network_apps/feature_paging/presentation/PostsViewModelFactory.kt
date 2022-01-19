package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetPostsUseCase

class PostsViewModelFactory(private val repository: PostRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            return PostsViewModel(GetPostsUseCase(repository)) as T
        }
        return super.create(modelClass)
    }
}