package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.ViewModel
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetPostsUseCase
import com.survivalcoding.network_apps.feature_paging.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: com.survivalcoding.network_apps.feature_paging.domain.usecase.GetPostsUseCase,
    private val getUserUseCase: com.survivalcoding.network_apps.feature_paging.domain.usecase.GetUserUseCase
) : ViewModel() {
}