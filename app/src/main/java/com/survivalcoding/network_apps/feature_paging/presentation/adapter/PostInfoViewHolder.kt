package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.ItemPostBinding
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import com.survivalcoding.network_apps.feature_paging.presentation.PostWithUserInfo

class PostInfoViewHolder(private val binding: ItemPostBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val CUSTOM_MAX_LINE = 50

    fun bind(item: PostWithUserInfo) {
        binding.postName.text = item.post.title
        binding.postContent.text = item.post.body
        binding.postContent.setOnClickListener {
            binding.postContent.maxLines =
                if (binding.postContent.maxLines == 1) CUSTOM_MAX_LINE else 1
        }
        item.user?.username?.let {
            binding.postOwner.text = it
        }
    }

    companion object {
        fun builder(parent: ViewGroup): PostInfoViewHolder = PostInfoViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}