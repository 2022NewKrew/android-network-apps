package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.ItemPostBinding
import com.survivalcoding.network_apps.feature_paging.domain.model.Post

class PostViewHolder(
    private val binding: ItemPostBinding,
    private val itemClickListener: (Post) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.postTvId.text = post.id.toString()
        binding.postTvTitle.text = post.title
        binding.postTvBody.text = post.body
        binding.root.setOnClickListener { itemClickListener(post) }
    }
}