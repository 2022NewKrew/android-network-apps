package com.survivalcoding.network_apps.paging.ui.posts.adapter.post

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.ListItemPostBinding
import com.survivalcoding.network_apps.paging.domain.model.Post
import com.survivalcoding.network_apps.paging.ui.posts.PostItem

class PostViewHolder(private val binding: ListItemPostBinding, private val onClick: (Int, Boolean) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        const val COLLAPSED_LINES = 1
        const val EXPANDED_LINES = 10
    }

    fun bind(postItem: PostItem?) {
        postItem?.run {
            binding.indexTv.text = post.id.toString()
            binding.titleTv.text = post.title
            binding.bodyTv.text = post.body
            binding.bodyTv.maxLines = if(isExpanded) EXPANDED_LINES else COLLAPSED_LINES
            binding.userNameTv.text = userName
        }

        binding.root.setOnClickListener {
            postItem?.let {
                binding.bodyTv.maxLines =
                    if (it.isExpanded) EXPANDED_LINES else COLLAPSED_LINES
                onClick(it.post.id, !it.isExpanded)
            }
        }
    }
}