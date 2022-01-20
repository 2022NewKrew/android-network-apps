package com.survivalcoding.network_apps.feature_paging.domain.usecase

import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class GetPostAndWriterUseCase @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getUserUseCase: GetUserUseCase
) {
    suspend operator fun invoke(page: Int): List<Pair<Post, User>> {
        val deferredPostWriters = mutableMapOf<Int, Deferred<User>>()

        val newPosts = getPostsUseCase(page).filter {
            it.userId != null
        }

        newPosts.forEach {
            deferredPostWriters[it.id ?: 0] = CoroutineScope(Dispatchers.IO).async {
                getUserUseCase(it.userId ?: 0)
            }
        }

        return newPosts.filter {
            deferredPostWriters[it.id] != null
        }.map {
            it to (deferredPostWriters[it.id]?.await() ?: User())
        }
    }
}