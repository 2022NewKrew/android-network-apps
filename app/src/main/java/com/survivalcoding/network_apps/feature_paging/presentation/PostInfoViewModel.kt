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

    private val requestProcess = mutableMapOf<Int, Int>()
/*    init {
       viewModelScope.launch {
           postRepository.getPosts().cachedIn(viewModelScope).collect {
               it.
           }
       }
    }*/

    // requesting = 1, // success = 2 // fail =3
    private fun getUserFromNet(id: Int) {
        if (requestProcess[id] == null) {
            requestProcess[id] = 1
            viewModelScope.launch {
                val newUserInfo = userRepository.getUserFromNet(id)
                newUserInfo?.id?.let { it ->
                    requestProcess[id] = 2
                    // _state.value.users[it] = newUserInfo
                    val m = mutableMapOf<Int, User>()
                    _state.value.users.toMap(m)
                    m[it] = newUserInfo
                    _state.value = _state.value.copy(users = m)
                }
                println(newUserInfo)
            }
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