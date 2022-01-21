package com.survivalcoding.network_apps.paging.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.survivalcoding.network_apps.paging.data.datasource.PostPagingSource
import com.survivalcoding.network_apps.paging.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val postsRepository: PostRepository) :
    ViewModel() {

    private val _post = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 40
        )
    ) {
        PostPagingSource(postsRepository)
    }.flow.cachedIn(viewModelScope)

    private val _userNameMap = MutableStateFlow(mapOf<Int, String>())
    private val _isExpanded = MutableStateFlow(mapOf<Int, Boolean>())

    val uiState = combine(_post, _userNameMap, _isExpanded) { post, userNameMap, isExpanded ->
        post.map {
            PostItem(
                it,
                userNameMap[it.userId] ?: run {
                    updateUserNameMap(it.userId)
                    ""
                },
                isExpanded[it.id] ?: run {
                    updateIsExpandedMap(it.id, false)
                    false
                }
            )
        }
    }.map { PostsUiState(it) }

    fun updateIsExpandedMap(postId: Int, isExpanded: Boolean) {
        _isExpanded.value = _isExpanded.value.toUpdated(postId to isExpanded)
    }

    private val userIdJobMap = mutableMapOf<Int, Job>()

    private fun updateUserNameMap(userId: Int) {
        if(userId !in userIdJobMap) {
            userIdJobMap[userId] = viewModelScope.launch {
                try {
                    _userNameMap.value = _userNameMap.value.toUpdated(userId to postsRepository.getUserById(userId).name)
                } finally {
                    userIdJobMap.remove(userId)
                }
            }
        }
    }

    private fun <K, V> Map<K, V>.toUpdated(vararg newEntries: Pair<K, V>): Map<K, V> {
        val filteredEntries = newEntries.filter { it.first !in this || (it.first in this && this[it.first] != it.second)}

        return if(filteredEntries.isEmpty()) {
            this
        } else {
            val newUserNameMap = mutableMapOf<K, V>()
            toMap(newUserNameMap)
            newUserNameMap.apply { filteredEntries.forEach { (k, v) -> set(k, v) } }
        }
    }
}