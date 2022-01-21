package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.PostListItemBinding
import com.survivalcoding.network_apps.feature_paging.domain.model.PostItem

class PostItemViewHolder(private val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(postItem: PostItem) {
        binding.textViewIndex.text = postItem.id.toString()
        binding.textViewTitle.text = postItem.title
        binding.textViewContent.text = postItem.body
        binding.textViewUsername.text = postItem.username
    }
}