package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.ItemPostBinding
import com.survivalcoding.network_apps.feature_paging.domain.model.Post

class PostInfoViewHolder(private val binding: ItemPostBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Post) {
        binding.postName.text = item.title
        binding.postContent.text = item.body
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