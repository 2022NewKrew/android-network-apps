package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.survivalcoding.network_apps.feature_paging.domain.model.PostItem
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {
    val pagingDataFlow: Flow<PagingData<PostItem>> = getPosts().cachedIn(viewModelScope)

    private fun getPosts(): Flow<PagingData<PostItem>> = repository.getPosts()
}