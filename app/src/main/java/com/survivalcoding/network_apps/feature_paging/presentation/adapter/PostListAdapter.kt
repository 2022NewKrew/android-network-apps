package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.network_apps.databinding.ItemPostBinding
import com.survivalcoding.network_apps.feature_paging.domain.model.Post

class PostListAdapter(
    private val itemClickListener: (Post) -> Unit
) : ListAdapter<Post, PostViewHolder>(PostDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemClickListener
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}