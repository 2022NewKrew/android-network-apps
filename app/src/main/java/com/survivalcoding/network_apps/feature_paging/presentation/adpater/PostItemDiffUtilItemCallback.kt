package com.survivalcoding.network_apps.feature_paging.presentation.adpater

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.feature_paging.presentation.PostItem

class PostItemDiffUtilItemCallback : DiffUtil.ItemCallback<PostItem>() {
    override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem) =
        oldItem.post.id == newItem.post.id

    override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem) = oldItem == newItem
}