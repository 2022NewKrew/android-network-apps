package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import com.survivalcoding.network_apps.feature_paging.domain.repository.UserRepository
import kotlinx.coroutines.launch

class PostInfoViewModel(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private var _state = PostInfoState(post = postRepository.getPosts().cachedIn(viewModelScope))
    val state: PostInfoState = _state
    
    fun getUserFromNet(id: Int) {
        viewModelScope.launch {
            val newUserInfo = userRepository.getUserFromNet(id)
            newUserInfo?.id?.let { it ->
                _state.users[it] = newUserInfo
            }
        }
    }

    fun getUserFromCache(id: Int): User? = _state.users[id]
}