package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import kotlinx.coroutines.*

class PostInfoListAdapter(
    private val userFromCache: (id: Int) -> User?,
    private val userFromNet: (id: Int) -> () -> User?
) : PagingDataAdapter<Post, PostInfoViewHolder>(PostInfoDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostInfoViewHolder =
        PostInfoViewHolder.builder(parent)

    override fun onBindViewHolder(holder: PostInfoViewHolder, position: Int) {
        getItem(position)?.let { post ->
            post.userId?.let { userId ->
                val cacheData = userFromCache(userId)
                if (cacheData == null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val testUser = async { userFromNet(userId)() }
                        val test = testUser.await()
                        withContext(Dispatchers.Main) {

                            println(test)
                            holder.bind(post, test)
                        }
                    }
                } else {
                    holder.bind(post, cacheData)
                }
            }

        }


    }
}