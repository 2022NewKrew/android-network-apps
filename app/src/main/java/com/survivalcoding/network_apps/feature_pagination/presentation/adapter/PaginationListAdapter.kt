package com.survivalcoding.network_apps.feature_pagination.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem

class PaginationListAdapter(
    private val clickEvent: (postItem: PostItem) -> Unit,
) : PagingDataAdapter<PostItem, PaginationListHolder>(
    object : DiffUtil.ItemCallback<PostItem>() {
        override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onBindViewHolder(holder: PaginationListHolder, position: Int) {
        holder.bind(getItem(position), clickEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaginationListHolder {
        return PaginationListHolder(parent)
    }
}