package com.survivalcoding.network_apps.paging.ui.posts.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.paging.domain.model.Post

object PostDiffItemCallback: DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}