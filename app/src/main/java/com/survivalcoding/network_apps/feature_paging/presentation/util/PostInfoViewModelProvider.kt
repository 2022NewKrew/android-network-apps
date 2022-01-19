package com.survivalcoding.network_apps.feature_paging.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import com.survivalcoding.network_apps.feature_paging.domain.repository.UserRepository
import com.survivalcoding.network_apps.feature_paging.presentation.PostInfoViewModel

class PostInfoViewModelProvider(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostInfoViewModel::class.java)) {
            return PostInfoViewModel(
                postRepository,
                userRepository
            ) as T
        }
        return super.create(modelClass)
    }
}