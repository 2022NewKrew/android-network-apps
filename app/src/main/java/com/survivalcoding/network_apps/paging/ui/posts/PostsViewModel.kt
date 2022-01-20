package com.survivalcoding.network_apps.paging.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.survivalcoding.network_apps.paging.data.datasource.PostPagingSource
import com.survivalcoding.network_apps.paging.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val postsRepository: PostRepository) : ViewModel() {

    val posts = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 40
        )
    ) {
        PostPagingSource(postsRepository)
    }.flow.cachedIn(viewModelScope)
}