package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import com.survivalcoding.network_apps.feature_paging.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostInfoViewModel(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private var _items = MutableStateFlow<PagingData<PostWithUserInfo>>(PagingData.empty())
    val items get(): Flow<PagingData<PostWithUserInfo>> = _items

    init {
        viewModelScope.launch {
            postRepository.getPosts().cachedIn(viewModelScope).collect {
                _items.value = it.map { post ->
                    if (post.userId == null) {
                        PostWithUserInfo(post = post, user = null) // userID가 없을 경우
                    } else {
                        val userInfo = userRepository.getUserFromCache(post.userId) // cache 확인
                        if (userInfo != null) {
                            PostWithUserInfo(post = post, user = userInfo)
                        } else {
                            // network 연결이 필요한 경우
                            userRepository.userRequestSet(post.userId)
                            val newUser = userRepository.getUserFromNet(post.userId)
                            PostWithUserInfo(post = post, user = newUser)
                        }
                    }
                }
            }
        }
    }


}



