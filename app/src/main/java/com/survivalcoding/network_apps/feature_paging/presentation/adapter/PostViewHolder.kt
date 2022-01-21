package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.ItemPostBinding
import com.survivalcoding.network_apps.feature_paging.domain.model.Post

class PostViewHolder(
    private val binding: ItemPostBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.postTvId.text = post.id.toString()
        binding.postTvTitle.text = post.title
        binding.postTvBody.text = post.body
        binding.postTvUsername.text = post.username
        binding.root.setOnClickListener {
            binding.postTvBody.maxLines = if (binding.postTvBody.maxLines == 1) Int.MAX_VALUE else 1
        }
    }
}