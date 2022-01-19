package com.survivalcoding.network_apps.paging.presentation.posts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.ItemPostBinding
import com.survivalcoding.network_apps.paging.domain.model.Post

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemPostBinding.bind(itemView)

    fun bind(post: Post) {
        binding.idText.text = post.id.toString()
        binding.bodyText.text = post.body
        binding.titleText.text = post.title
    }
}