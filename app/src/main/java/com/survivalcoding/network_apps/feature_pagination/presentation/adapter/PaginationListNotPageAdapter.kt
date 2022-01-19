package com.survivalcoding.network_apps.feature_pagination.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem

class PaginationListNotPageAdapter(
    private val clickEvent: (postItem: PostItem) -> Unit,
) : ListAdapter<PostItem, PaginationListHolder>(
    object : DiffUtil.ItemCallback<PostItem>() {
        override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaginationListHolder {
        return PaginationListHolder(parent)
    }

    override fun onBindViewHolder(holder: PaginationListHolder, position: Int) {
        holder.bind(getItem(position), clickEvent)
    }
}