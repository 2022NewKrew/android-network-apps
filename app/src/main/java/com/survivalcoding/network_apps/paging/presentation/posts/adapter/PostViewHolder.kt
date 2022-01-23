package com.survivalcoding.network_apps.paging.presentation.posts.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.ItemPostBinding
import com.survivalcoding.network_apps.paging.domain.model.PostWithName

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemPostBinding.bind(itemView)

    fun bind(post: PostWithName) {
        binding.idText.text = post.id.toString()
        binding.bodyText.text = post.body
        binding.titleText.text = post.title
        binding.nameText.text = itemView.resources.getString(R.string.user, post.user)
        itemView.setOnClickListener {
            binding.bodyText.maxLines = if (binding.bodyText.maxLines == 1) BODY_MAX else 1
            binding.titleText.maxLines = if (binding.titleText.maxLines == 1) TITLE_MAX else 1
        }
    }

    companion object {
        const val BODY_MAX = 50
        const val TITLE_MAX = 5
    }
}