package com.survivalcoding.network_apps.feature_pagination_v2.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.PaginationPostListItemBinding
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem

class PostListHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.pagination_post_list_item, parent, false)
) {
    private val binding = PaginationPostListItemBinding.bind(itemView)

    fun bind(item: PostItem) {
        binding.tvPostTitle.text = item.postContent.title
        binding.tvPostName.text = item.name
        binding.tvPostNumber.text = item.id.toString()
        binding.tvPostContent.text = item.postContent.body

        if (item.isExpanded) binding.tvPostContent.maxLines = Int.MAX_VALUE
        else binding.tvPostContent.maxLines = 1
    }
}