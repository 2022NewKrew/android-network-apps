package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
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

    private var _state =
        MutableStateFlow(PostInfoState(post = postRepository.getPosts().cachedIn(viewModelScope)))
    val state: Flow<PostInfoState> = _state

/*    init {
       viewModelScope.launch {
           postRepository.getPosts().cachedIn(viewModelScope).collect {
               it.
           }
       }
    }*/

    private fun getUserFromNet(id: Int) {
        viewModelScope.launch {
            val newUserInfo = userRepository.getUserFromNet(id)
            newUserInfo?.id?.let { it ->
                _state.value.users[it] = newUserInfo
            }
            println(newUserInfo)
        }
    }

    fun getUserFromCache(id: Int): User? = _state.value.users[id]

    fun eventHandler(event: TestEvent) {
        when (event) {
            is TestEvent.RequestCache -> {
                getUserFromCache(event.id)
            }
            is TestEvent.RequestNet -> {
                getUserFromNet(event.id)
            }
            is TestEvent.ResultNet -> {

            }

        }
    }

}

data class PostWithUserInfo(
    private val post: Post,
    private val user: User
)

sealed class TestEvent {
    class RequestCache(val id: Int) : TestEvent()
    class RequestNet(val id: Int) : TestEvent()
    class ResultNet : TestEvent()
}