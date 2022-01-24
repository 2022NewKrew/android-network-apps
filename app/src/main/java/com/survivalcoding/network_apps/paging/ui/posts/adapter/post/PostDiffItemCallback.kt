package com.survivalcoding.network_apps.paging.ui.posts.adapter.post

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.paging.domain.model.Post
import com.survivalcoding.network_apps.paging.ui.posts.PostItem

object PostDiffItemCallback: DiffUtil.ItemCallback<PostItem>() {
    override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
        return oldItem.post.id == newItem.post.id
    }

    override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
        return oldItem == newItem
    }
}