package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.network_apps.feature_paging.domain.model.Post

class PostInfoListAdapter :
    ListAdapter<Post, PostInfoViewHolder>(PostInfoDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostInfoViewHolder =
        PostInfoViewHolder.builder(parent)

    override fun onBindViewHolder(holder: PostInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}