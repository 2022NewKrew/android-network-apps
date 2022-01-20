package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.network_apps.feature_paging.domain.model.Post

class PostInfoListAdapter : PagingDataAdapter<Post, PostInfoViewHolder>(PostInfoDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostInfoViewHolder =
        PostInfoViewHolder.builder(parent)

    override fun onBindViewHolder(holder: PostInfoViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}