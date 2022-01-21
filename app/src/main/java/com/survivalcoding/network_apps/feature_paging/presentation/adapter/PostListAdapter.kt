package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.survivalcoding.network_apps.databinding.PostListItemBinding
import com.survivalcoding.network_apps.feature_paging.data.datasource.PostDiffUtilCallback
import com.survivalcoding.network_apps.feature_paging.domain.model.PostItem

class PostListAdapter: PagingDataAdapter<PostItem, PostItemViewHolder>(PostDiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        val post = getItem(position)
        post?.let {holder.bind(it)}
    }

}