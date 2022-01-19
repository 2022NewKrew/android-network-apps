package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.feature_paging.domain.model.Post

/*
Note that DiffUtil, ListAdapter, and AsyncListDiffer require the list to not mutate while in use.
 This generally means that both the lists themselves and their elements
 (or at least, the properties of elements used in diffing) should not be modified directly.
 Instead, new lists should be provided any time content changes.
 It's common for lists passed to DiffUtil to share elements that have not mutated,
 so it is not strictly required to reload all data to use DiffUtil.
* */
object PostInfoDiffItemCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}