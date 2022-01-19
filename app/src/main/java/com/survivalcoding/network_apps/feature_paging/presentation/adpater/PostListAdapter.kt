package com.survivalcoding.network_apps.feature_paging.presentation.adpater

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.network_apps.feature_paging.presentation.PostItem

class PostListAdapter : ListAdapter<PostItem, PostItemViewHolder>(PostItemDiffUtilItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
