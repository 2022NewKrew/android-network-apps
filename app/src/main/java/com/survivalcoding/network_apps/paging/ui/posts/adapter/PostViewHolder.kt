package com.survivalcoding.network_apps.paging.ui.posts.adapter

import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.ListItemPostBinding
import com.survivalcoding.network_apps.paging.domain.model.Post

class PostViewHolder(private val binding: ListItemPostBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post?) {
        post?.run {
            binding.indexTv.text = id.toString()
            binding.titleTv.text = title
            binding.bodyTv.text = body
            binding.userNameTv.text = post.userName ?: ""
        }
    }
}