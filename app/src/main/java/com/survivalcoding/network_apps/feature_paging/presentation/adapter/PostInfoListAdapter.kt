package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import kotlinx.coroutines.*

class PostInfoListAdapter(
    private val userFromCache: (id: Int) -> User?,
    private val userFromNet: (id: Int) -> Unit
) : PagingDataAdapter<Post, PostInfoViewHolder>(PostInfoDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostInfoViewHolder =
        PostInfoViewHolder.builder(parent)

    override fun onBindViewHolder(holder: PostInfoViewHolder, position: Int) {
        getItem(position)?.let { post ->
            post.userId?.let { userId ->
                val cacheData = userFromCache(userId)
                if (cacheData == null) {
                    userFromNet(userId)
                }
                println("in adapter=>"+userFromCache(userId))
                holder.bind(post, cacheData)
            }
        }
    }
}