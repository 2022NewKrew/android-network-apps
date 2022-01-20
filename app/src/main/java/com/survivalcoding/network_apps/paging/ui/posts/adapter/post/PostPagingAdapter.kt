package com.survivalcoding.network_apps.paging.ui.posts.adapter.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.survivalcoding.network_apps.databinding.ListItemPostBinding
import com.survivalcoding.network_apps.paging.domain.model.Post

class PostPagingAdapter() : PagingDataAdapter<Post, PostViewHolder>(PostDiffItemCallback) {
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ListItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}