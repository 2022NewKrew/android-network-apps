package com.survivalcoding.network_apps.feature_paging.presentation.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.PostItemBinding
import com.survivalcoding.network_apps.feature_paging.presentation.PostItem

class PostItemViewHolder private constructor(private val binding: PostItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(postItem: PostItem) {
        binding.idTextView.text = postItem.post.id.toString()
        binding.titleTextView.text = postItem.post.title
        binding.bodyTextView.text = postItem.post.body
        binding.userNameTextView.text = postItem.user.username
        binding.bodyTextView.setOnClickListener {
            (it as TextView).maxLines = if (it.maxLines == 1) {
                Integer.MAX_VALUE
            } else {
                1
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): PostItemViewHolder {
            return PostItemViewHolder(
                PostItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}