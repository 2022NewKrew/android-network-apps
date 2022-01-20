package com.survivalcoding.network_apps.paging.ui.posts.adapter

import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.ListItemPostBinding
import com.survivalcoding.network_apps.paging.domain.model.Post

class PostViewHolder(private val binding: ListItemPostBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        const val COLLAPSED_LINES = 1
        const val EXPANDED_LINES = 10
    }

    fun bind(post: Post?) {
        post?.run {
            binding.indexTv.text = id.toString()
            binding.titleTv.text = title
            binding.bodyTv.text = body
            binding.bodyTv.maxLines = COLLAPSED_LINES
            binding.userNameTv.text = post.userName ?: ""
        }

        binding.root.setOnClickListener {
            binding.bodyTv.maxLines =
                if (binding.bodyTv.maxLines > COLLAPSED_LINES) COLLAPSED_LINES else EXPANDED_LINES
        }
    }
}