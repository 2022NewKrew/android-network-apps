package com.survivalcoding.network_apps.paging.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.paging.domain.model.PostWithName

class PostPagingDataAdapter :
    PagingDataAdapter<PostWithName, PostViewHolder>(PostDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    object PostDiffItemCallback : DiffUtil.ItemCallback<PostWithName>() {
        override fun areItemsTheSame(oldItem: PostWithName, newItem: PostWithName): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostWithName, newItem: PostWithName): Boolean {
            return oldItem == newItem
        }
    }
}