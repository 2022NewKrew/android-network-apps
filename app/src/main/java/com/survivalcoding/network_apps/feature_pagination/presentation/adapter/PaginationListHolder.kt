package com.survivalcoding.network_apps.feature_pagination.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.PaginationPostListItemBinding
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem

class PaginationListHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.pagination_post_list_item, parent, false)
) {
    private val binding = PaginationPostListItemBinding.bind(itemView)

    fun bind(
        postItem: PostItem?,
        clickEvent: (postItem: PostItem) -> Unit,
    ) {
        postItem?.let {
            binding.tvPostTitle.text = postItem.postContent.title
            binding.tvPostName.text = postItem.name
            binding.tvPostContent.text = postItem.postContent.body
            binding.tvPostNumber.text = with(postItem.postContent.id) {
                if (this < 10) "0$this" else this.toString()
            }

//            itemView.setOnClickListener {
//                binding.tvPostContent.maxLines =
//                    if (binding.tvPostContent.maxLines == 1) Int.MAX_VALUE
//                    else 1
//            }
            binding.tvPostContent.maxLines =
                if (postItem.isExpanded) Int.MAX_VALUE
                else 1

            itemView.setOnClickListener {
                clickEvent(postItem)
            }
        }
    }
}